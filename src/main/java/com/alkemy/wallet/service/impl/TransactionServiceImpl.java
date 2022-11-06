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

        transaction.setType(Type.payment);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction saveDeposit(Transaction transaction) throws Exception {

        transaction.setType(Type.deposit);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByAccount(Long id) {
        return transactionRepository.findByAccount(id);
    }


}
