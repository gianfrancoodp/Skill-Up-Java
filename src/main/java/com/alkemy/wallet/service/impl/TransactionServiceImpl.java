package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserService userService;

    @Override
    public Transaction savePayment(Transaction transaction) throws Exception {
        LocalDate date = LocalDate.now();
        Transaction result;
        if(accountService.limitTransactions(date,transaction) && accountService.accountFunds(transaction, transaction.getAccount().getAccountId())) {
            transaction.setType(Type.payment);
            result = transactionRepository.save(transaction);
        }
        else
            throw new Exception("The diary transaction limit has been reached or your account funds are not enough");
        return result;
    }

    @Override
    public Transaction saveDeposit(Transaction transaction) throws Exception {
        LocalDate date = LocalDate.now();
        Transaction result;
        if(accountService.limitTransactions(date,transaction) && accountService.accountFunds(transaction, transaction.getAccount().getAccountId())) {
            transaction.setType(Type.deposit);
            result = transactionRepository.save(transaction);
        }
        else
            throw new Exception("The diary transaction limit has been reached or your account funds are not enough");
        return result;
    }



    @Override
    public List<Transaction> findByAccount(Long id) {

        return transactionRepository.findByAccount(id);

    }







}
