package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findByAccount(Long id);
    public List<Transaction> findByAccountAndTransactionDate(Long id, LocalDate date);
}
