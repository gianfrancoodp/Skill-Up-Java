package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.service.ITransactionService;
import org.springframework.boot.util.LambdaSafe;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class TransactionController {
    private ITransactionService service;


    public TransactionController(ITransactionService service) {
        this.service = service;
    }


    @GetMapping("/transactions/{userId}")
    public List<TransactionDto> listTransactions(@PathVariable("userId") long id) {
        return service.listUserId(id);
    }

    @GetMapping("/transactions/:id{id}")
    public TransactionDto transactionDetail(@PathVariable("id") long id) {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        TransactionDto transaction = new TransactionDto();
        long idUser = 0;
        for (int i = 0; i < service.listUser().size(); i++) {
            if (service.listUser().get(i).getEmail().toString().equals(principal)) {
                idUser = service.listUser().get(i).getId();
            }
        }
        List<TransactionDto> listTransactions = service.listUserId(idUser);
        for (int j = 0; j < listTransactions.size(); j++) {
            if (listTransactions.get(j).getId() == id) {
                transaction = listTransactions.get(j);
            }
        }
        if (transaction != null) {

            return transaction;
        } else throw new RuntimeException();

    }

    @PatchMapping("/transactions/:id{id}")
    public TransactionDto editTransaction(@PathVariable("id") long id, @RequestBody String description) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
        TransactionDto transaction = service.listDetail(id);
        long idUser = 0;
        for (int i = 0; i < service.listUser().size(); i++) {
            if (service.listUser().get(i).getEmail().equals(principal)) {
                idUser = service.listUser().get(i).getId();
            }
        }
        List<TransactionDto> listTransactions = service.listUserId(idUser);
        if (listTransactions.contains(transaction)) {
            transaction.setDescription(description);
            return service.edit(transaction);
        } else throw new RuntimeException();
    }
}