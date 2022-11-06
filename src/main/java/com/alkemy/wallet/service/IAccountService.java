package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface IAccountService {

    public List<Account> findAccountByUserId(Long id) throws Exception;
    public void transactionLimitCount(Account account);

    public Optional<Account> findById(Long id) throws Exception;

    public List<Account> findByUserId(Long id);

    /**
     * returns true if the account Id belongs to the authenticated user, or false if it doesn't.
     * @param accountId, userEmail
     * @return boolean
     */
    public boolean accountUser(Long accountId, String userEmail) throws Exception;

    public boolean limitTransactions(LocalDate date, Transaction transaction);

}
