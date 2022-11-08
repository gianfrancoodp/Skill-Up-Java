package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Transaction result;
        if(accountService.limitTransactions(transaction) && accountService.accountFunds(transaction))
        {
            transaction.setType(Type.payment);
            accountService.accountBalance(transaction);
            result = transactionRepository.save(transaction);
        }
        else
            throw new Exception("The diary transaction limit has been reached or your account funds are not enough");
        return result;
    }

    @Override
    public Transaction saveDeposit(Transaction transaction) throws Exception {
        Transaction result;
        if(accountService.limitTransactions(transaction) && accountService.accountFunds(transaction)) {
            transaction.setType(Type.deposit);
            accountService.accountBalance(transaction);
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
