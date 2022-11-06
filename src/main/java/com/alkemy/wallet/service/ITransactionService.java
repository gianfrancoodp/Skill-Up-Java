package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface ITransactionService {

    /**
     * This method saves a payment in the database.
     * @param transaction
     * @return Transaction
     * @throws Exception
     */
    public Transaction savePayment(Transaction transaction) throws Exception;

    /**
     * This method saves a deposit in the database.
     * @param transaction
     * @return
     * @throws Exception
     */
    public Transaction saveDeposit(Transaction transaction) throws Exception;

}
