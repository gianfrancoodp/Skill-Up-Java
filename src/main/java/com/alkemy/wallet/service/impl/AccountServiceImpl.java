package com.alkemy.wallet.service.impl;



import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.mapper.AccountMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IFixedTermDepositService;
import com.alkemy.wallet.util.CurrencyEnum;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

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
    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private IFixedTermDepositService iFixedTermDepositService;

    @Override
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
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findByUserId(Long id) {
        return accountRepository.findByUserId(id);
    }

    public boolean accountUser(Long id, String userEmail) throws Exception {
        if (accountRepository.findByUserId(userService.findByEmail(userEmail).getId()).isEmpty())
            return false;
        else
            return true;
    }


    @Override
    public boolean limitTransactions(Transaction transaction) {
        try{
            return  (transactionRepository.findByAccountIdAndTransactionDate(transaction.getAccount().getAccountId()).size())<=transaction.getAccount().getTransactionLimit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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
}
