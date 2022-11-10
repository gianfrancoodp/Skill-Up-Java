package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean accountFunds(Transaction transaction) {
        try {
            return (transaction.getAmount() <= accountRepository.findById(transaction.getAccount().getAccountId()).get().getBalance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
