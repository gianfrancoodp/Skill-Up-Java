package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.TransactionPaymentDto;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    /**
     * Inserts a new payment on table TRANSACTIONS into the database.
     * @param dto
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping
    @RequestMapping("/payment")
    public ResponseEntity<Object> payment(@RequestBody TransactionPaymentDto dto) throws Exception {
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transactionService.savePayment(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping
    @RequestMapping("/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionPaymentDto dto) throws Exception {
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transactionService.savePayment(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
