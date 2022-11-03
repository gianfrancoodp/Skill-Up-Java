package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Transaction;

public interface TransactionService {
    Transaction listId(long id);

    Transaction listDetail(long id);
    Transaction edit (Transaction t);

}
