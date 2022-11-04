package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository repo;

    @Override
    public List<Transaction> listUserId(long id){
        return repo.findByUserId(id);
    }

    @Override
    public Transaction listDetail(long id) {
        return repo.findByid(id);
    }

    @Override
    public Transaction edit (Transaction t){
        return repo.save(t);
    }
}

