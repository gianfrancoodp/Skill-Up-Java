package com.alkemy.wallet.service.impl;


import com.alkemy.wallet.dto.AccountDto; 
import com.alkemy.wallet.mapper.AccountAssembler;
import com.alkemy.wallet.exception.UserNotFoundException; 
import com.alkemy.wallet.mapper.AccountMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IFixedTermDepositService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.CurrencyEnum;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AccountMapper accountMapper;

    private IUserService userService;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private AccountAssembler accountAssembler;
    @Autowired
    private PagedResourcesAssembler<Account> pagedResourcesAssembler;


    private IFixedTermDepositService iFixedTermDepositService;

    @Autowired
    public AccountServiceImpl(@Lazy IFixedTermDepositService iFixedTermDepositService,
                              @Lazy IUserService userService) {
        this.iFixedTermDepositService = iFixedTermDepositService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public AccountDto createAccount(CurrencyEnum currency, long idUser) throws Exception {

        Optional<UserEntity> find = userRepository.findById(idUser);

        if (find.isPresent()) {

            AccountDto request = transactionLimitCreateAccount(currency);

            if (accountRepository.queryAccountCurrencyUSD(idUser, request.getCurrency()).isEmpty()) {
                System.out.println("-----USD");
                Account entity = accountMapper.map(request);
                entity.setUserId(find.get());
                accountRepository.save(entity);
                return accountMapper.map(entity);

            } else if (accountRepository.queryAccountCurrencyARS(idUser, request.getCurrency()).isEmpty()) {
                System.out.println("-----ARS");
                Account entity = accountMapper.map(request);
                entity.setUserId(find.get());
                accountRepository.save(entity);
                return accountMapper.map(entity);

            } else {
                //add exception
                return null;
            }

        } else {
            //add exception
            return null;
        }
    }

    @Override
    public List<Account> findAccountByUserId(Long id) throws Exception {
        return accountRepository.findByUserId(id);
    }

    @Override
    public Optional<Account> findById(Long id) throws Exception {
       try{
           return accountRepository.findById(id);

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<Account> findByUserId(Long id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public void accountBalance(Transaction transaction) throws Exception {
        Account account = null;
        if(accountRepository.findById(transaction.getAccount().getAccountId()).isPresent()) {
            if(transaction.getType().equals(Type.payment)){
                account = accountRepository.findById(transaction.getAccount().getAccountId()).get();
                account.setBalance(accountRepository.findById(transaction.getAccount().getAccountId()).get().getBalance() - transaction.getAmount());
            } else if (transaction.getType().equals(Type.deposit)) {
                account = accountRepository.findById(transaction.getAccount().getAccountId()).get();
                account.setBalance(accountRepository.findById(transaction.getAccount().getAccountId()).get().getBalance() + transaction.getAmount());
            }
            accountRepository.save(account);
        }
        else
            throw new Exception("The account has not been found in the database.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> accountList(long idUser) throws Exception {
        Optional<UserEntity> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            List<Account> listAccount = accountRepository.accountList(idUser);
            return accountMapper.map(listAccount);
        } else {
            //add exception
            return null;
        }

    }

    private AccountDto transactionLimitCreateAccount(CurrencyEnum currency) {
        AccountDto accountDto = new AccountDto();
        accountDto.setBalance(0.0);
        if (currency.getValor().equalsIgnoreCase("USD")) {
            accountDto.setTransactionLimit(1000.0);
            accountDto.setCurrency(currency);
        } else if (currency.getValor().equalsIgnoreCase("ARS")) {
            accountDto.setTransactionLimit(300000.0);
            accountDto.setCurrency(currency);
        }
        return accountDto;
    }

    public boolean accountFunds(Transaction transaction) {
        try {
            return (transaction.getAmount() <= accountRepository.findById(transaction.getAccount().getAccountId()).get().getBalance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public AccountDto updateAccount(Long idUser ,AccountDto accountDto) throws Exception {
        Optional<UserEntity> find = userRepository.findById(idUser);

        if (find.isPresent()) {
            if (!find.get().isDeleted()) {
                if (accountDto.getCurrency().getValor().equals("ARS")) {
                    Account entity = accountRepository.queryAccountCurrencyARS(idUser, accountDto.getCurrency()).get();
                    entity.setTransactionLimit(accountDto.getTransactionLimit());
                    accountRepository.save(entity);
                    return accountMapper.map(entity);
                } else {
                    Account entity = accountRepository.queryAccountCurrencyUSD(idUser, accountDto.getCurrency()).get();
                    entity.setTransactionLimit(accountDto.getTransactionLimit());
                    accountRepository.save(entity);
                    return accountMapper.map(entity);
                }
            } else {
                new UserNotFoundException("The User with ID " + idUser + " was deleted.");
            }
        } else {
            new UserNotFoundException("There is no user with that ID!");
        }
        return null;
    }

    @Override
    public Account findById(long id) throws ChangeSetPersister.NotFoundException {
        return accountRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Map<String, Double> getAccountsBalance(UserEntity userId) throws Exception {
        Map<String, Double> balance = new HashMap<>();
        accountRepository
                .findAll()
                .stream()
                .filter(acc -> acc.getUserId().equals(userId))
                .forEach(acc -> {
                    balance.put(acc.getCurrency().toString(), acc.getBalance());
                });
        List<FixedTermDeposit> list = iFixedTermDepositService.findAll().stream().filter(ftd -> ftd.getUserEntity().equals(userId)).toList();
        if (list.size()>0){
            list.forEach(ftd-> {
                balance.put("FTD: " + ftd.getAccount().getAccountId(), ftd.getAmount());
            });
        }

        return balance;
    }

    @Override
    public PagedModel<AccountDto> findAll(Integer page) throws Exception {
        try {
            PageRequest pageRequest = PageRequest.of(0,10);
            if(page != null) {
                pageRequest = PageRequest.of(page, 10);
            }
            Page<Account> accounts = accountRepository.findAll(pageRequest);
            PagedModel<AccountDto> accountsDto = pagedResourcesAssembler.toModel(accounts, accountAssembler);
            return accountsDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}