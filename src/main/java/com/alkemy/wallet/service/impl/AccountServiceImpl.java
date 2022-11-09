package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.mapper.AccountMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.util.CurrencyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AccountMapper accountMapper;

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

}
