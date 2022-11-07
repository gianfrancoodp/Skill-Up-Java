package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.service.ITransactionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class TransactionController {
    private ITransactionService service;



    public TransactionController( ITransactionService service) {
        this.service = service;
    }


    @GetMapping("/transactions/:userId{userId}")
    public List<TransactionDto> listTransactions(@PathVariable("userId") long id) {
        return service.listUserId(id);
    }

    @GetMapping("/transactions/:id{id}")
    public TransactionDto transactionDetail(@PathVariable("id") long id) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        TransactionDto transaction = new TransactionDto();
        for (int i=0; i< service.listUser().size(); i++){
            if (service.listUser().get(i).getEmail().toString().equals(principal)){
                transaction = service.listDetail(id);
            }
        }
        if (transaction != null){
            return transaction;}
        else {
            throw new RuntimeException();
        }

    }

    @PatchMapping("/transactions/:id{id}")
    public TransactionDto editTransaction(@PathVariable("id") long id,@RequestBody String description) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TransactionDto transaction = new TransactionDto();
        for (int i = 0; i < service.listUser().size(); i++) {
            if (service.listUser().get(i).getEmail().toString().equals(principal)) {
                transaction = service.listDetail(id);
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
