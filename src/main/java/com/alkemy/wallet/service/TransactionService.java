package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> listUserId(long id);
    Transaction listDetail(long id);
    Transaction edit (Transaction t);

}
