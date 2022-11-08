package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findByAccount(Long id);
    @Query("SELECT t FROM Transaction t WHERE t.account = id  AND t.transactionDate = CURRENT_TIMESTAMP")
    public List<Transaction> findByAccountIdAndTransactionDate(@Param("id") Long id);
}
