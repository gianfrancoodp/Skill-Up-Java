package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findByAccount(Long id);
}
