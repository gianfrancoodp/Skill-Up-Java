package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Foo;
import com.alkemy.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();
    Transaction findByid (long id);
    Transaction save(Transaction t);




}
