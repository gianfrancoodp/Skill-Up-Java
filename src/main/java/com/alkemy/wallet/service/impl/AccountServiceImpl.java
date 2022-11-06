package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public List<Account> findAccountByUserId(Long id) throws Exception {
        return accountRepository.findByUserId(id);
    }

    @Override
    public void transactionLimitCount(Account account) {

    }

    @Override
    public Optional<Account> findById(Long id) throws Exception {
        return accountRepository.findById(id);
    }
}
