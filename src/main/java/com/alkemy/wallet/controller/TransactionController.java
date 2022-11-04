package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.repository.UserRepository;
import com.alkemy.wallet.service.TransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TransactionController {
    private TransactionRepository repo;
    private TransactionService service;

    private UserRepository userRepo;


    public TransactionController(TransactionRepository repo, TransactionService service, UserRepository userRepo) {
        this.repo = repo;
        this.service = service;
        this.userRepo=userRepo;
    }


    @GetMapping("/transactions/:userId{userId}")
    public List<Transaction> listTransactions(@PathVariable("userId") long id) {

        return service.listUserId(id);
    }

    @GetMapping("/transactions/:id{id}")
    public Transaction transactionDetail(@PathVariable("id") long id) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        Transaction transaction = new Transaction();
        for (int i=0; i< userRepo.findAll().size(); i++){
            if (userRepo.findAll().get(i).getEmail().toString().equals(principal)){
              transaction = service.listDetail(id);
            }
        }
        if (transaction != null){
        return transaction;}
        else {
            throw new RuntimeException();
        }

    }

    @PatchMapping("/transactions/{id}")
    public Transaction editTransaction(@PathVariable("id") long id,@RequestBody String description) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Transaction transaction = new Transaction();
        for (int i = 0; i < userRepo.findAll().size(); i++) {
            if (userRepo.findAll().get(i).getEmail().toString().equals(principal)) {
                transaction = repo.findByid(id);
                transaction.setDescription(description);
                return service.edit(transaction);
            }

        }
        if (transaction != null){
            return transaction;}
        else {
            throw new RuntimeException();
        }
    }


}
