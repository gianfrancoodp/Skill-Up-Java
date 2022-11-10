package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Transaction;
import org.springframework.stereotype.Service;
import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.util.CurrencyEnum;
import java.util.List;
import java.util.Optional;

import java.util.List;

public interface IAccountService {

    public AccountDto createAccount(CurrencyEnum currency, long idUser) throws Exception;

    public List<AccountDto> accountList(long idUser) throws Exception;

    public List<Account> findAccountByUserId(Long id) throws Exception;

    public Optional<Account> findById(Long id) throws Exception;

    public List<Account> findByUserId(Long id);

    /**
     * returns true if the account Id belongs to the authenticated user, or false if it doesn't.
     * @param accountId, userEmail
     * @return boolean
     */
    public boolean accountUser(Long accountId, String userEmail) throws Exception;

    public boolean limitTransactions(Transaction transaction);



    public void accountBalance(Transaction transaction) throws Exception;

    public boolean accountFunds(Transaction transaction);

}
