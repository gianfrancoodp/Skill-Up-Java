package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.TransactionPaymentDto;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.security.util.JwtUtils;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.boot.util.LambdaSafe;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping
public class TransactionController {


    @Autowired
    private ITransactionService service;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtils jwtUtils;



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



    /**
     * Inserts a new payment on table TRANSACTIONS into the database.
     * @param dto
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping
    @RequestMapping("transactions/payment")
    public ResponseEntity<Object> payment(@RequestBody TransactionPaymentDto dto, @RequestHeader(name="Authorization") String token) throws Exception {
       String email = jwtUtils.extractUsername(token);
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transaction.setUser(userService.findByEmail(email));
        TransactionPaymentDto result = new TransactionMapper().entityToDto(service.savePayment(transaction));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping
    @RequestMapping("transactions/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionPaymentDto dto, @RequestHeader(name="Authorization") String token) throws Exception {
        String email = jwtUtils.extractUsername(token);
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transaction.setUser(userService.findByEmail(email));
        TransactionPaymentDto result = new TransactionMapper().entityToDto(service.saveDeposit(transaction));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

