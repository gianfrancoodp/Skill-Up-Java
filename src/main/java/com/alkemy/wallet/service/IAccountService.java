package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IAccountService {

    public List<Account> findAccountByUserId(Long id) throws Exception;
    public void transactionLimitCount(Account account);

    public Optional<Account> findById(Long id) throws Exception;

    public List<Account> findByUserId(Long id);


}
