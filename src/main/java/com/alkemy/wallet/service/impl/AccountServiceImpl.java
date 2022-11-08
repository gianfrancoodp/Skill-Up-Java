package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IFixedTermDepositService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.CurrencyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IFixedTermDepositService iFixedTermDepositService;


    @Override
    public Account findById(long id) throws ChangeSetPersister.NotFoundException {
        return accountRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Map<String, Double> getAccountsBalance(User userId) throws Exception {
        Map<String, Double> balance = new HashMap<>();
        accountRepository
                .findAll()
                .stream()
                .filter(acc -> acc.getUserId().equals(userId))
                .forEach(acc -> {
                    balance.put(acc.getCurrency().toString(), acc.getBalance());
                });
        List<FixedTermDeposit> list = iFixedTermDepositService.findAll().stream().filter(ftd -> ftd.getUser().equals(userId)).toList();
        if (list.size()>0){
            list.forEach(ftd-> {
                balance.put("FTD: " + ftd.getAccount().getAccountId(), ftd.getAmount());
            });
        }

        return balance;
    }
}
