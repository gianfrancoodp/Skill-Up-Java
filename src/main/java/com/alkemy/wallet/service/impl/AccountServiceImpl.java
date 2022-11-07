package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.mapper.AccountMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
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
    public AccountDto createAccount(AccountDto request , Long idUser) {

        Optional<User> find = userRepository.findById(idUser);

        if (find.isPresent()){

            if (accountRepository.queryAccountCurrencyUSD(idUser , request.getCurrency()) == null){

                Account entity = accountMapper.map(request);
                accountRepository.save(entity);
                return accountMapper.map(entity);

            } else if(accountRepository.queryAccountCurrencyARS(idUser , request.getCurrency()) == null){

                Account entity = accountMapper.map(request);
                accountRepository.save(entity);
                return accountMapper.map(entity);

            } else {
                return null;
            }

        } else {
            return null;
        }


    }

    @Override
    public List<AccountDto> accountList(Long idUser) {

        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            List<Account> listAccount = accountRepository.accountList(idUser);
            return accountMapper.map(listAccount);
        } else {
            return null;
        }

    }

    @Override
    public Double myBalance(Long idUser) {
        //method for return balance(ARS and USD) in account
        return null;
    }


}
