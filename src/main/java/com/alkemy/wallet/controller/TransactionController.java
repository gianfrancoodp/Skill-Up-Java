package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.TransactionPaymentDto;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.security.util.JwtUtils;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Inserts a new payment on table TRANSACTIONS into the database.
     * @param dto
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping
    @RequestMapping("/payment")
    public ResponseEntity<Object> payment(@RequestBody TransactionPaymentDto dto, @RequestHeader(name="Authorization") String token) throws Exception {
       String email = jwtUtils.extractUsername(token);
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transaction.setUser(userService.findByEmail(email));
        transactionService.savePayment(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping
    @RequestMapping("/deposit")
    public ResponseEntity<Object> deposit(@RequestBody TransactionPaymentDto dto, @RequestHeader(name="Authorization") String token) throws Exception {
        String email = jwtUtils.extractUsername(token);
        Transaction transaction = new TransactionMapper().dtoToEntity(dto);
        transaction.setUser(userService.findByEmail(email));
        transactionService.savePayment(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
