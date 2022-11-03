package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TransactionController {
    private TransactionRepository repo;
    private TransactionService service;

    public TransactionController (TransactionRepository repo, TransactionService service){
        this.repo=repo;
        this.service=service;
    }

    @GetMapping("/transactions/{userId}")
    public Transaction listTransactions(@PathVariable("userId") long id) {
        return  service.listDetail(id);
    }

    @GetMapping("/transactions/{id}")
    public Transaction transactionDetail(@PathVariable("id") long id) {
        return  service.listId(id);
    }

    @PatchMapping("/transactions/{id}")
    public  Transaction editTransaction(@PathVariable("id") long id){
        Transaction transaction = repo.findByid(id);
        if (transaction != null){
            return service.edit(transaction);
        }
        
    }
}
