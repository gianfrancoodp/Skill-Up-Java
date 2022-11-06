package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionRepository transactionRepository;


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
    public boolean limitTransactions(LocalDate date, Transaction transaction) {

        return  (transactionRepository.findByAccountAndTransactionDate(transaction.getAccount().getAccountId(),date).size())<=transaction.getAccount().getTransactionLimit();

    }
}
