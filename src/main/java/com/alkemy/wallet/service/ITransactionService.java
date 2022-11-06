package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     *
     * @param id
     * @return List<Transaction>
     */
    public List<Transaction> findByAccount(Long id);



}
