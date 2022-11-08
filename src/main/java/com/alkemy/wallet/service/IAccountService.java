package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.util.CurrencyEnum;
import com.alkemy.wallet.util.Type;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Map;

public interface IAccountService {
    Account findById(long id) throws ChangeSetPersister.NotFoundException;
    List<Account> findAll();

    Map<String, Double> getAccountsBalance(User userId) throws Exception;

}
