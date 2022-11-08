package com.alkemy.wallet.controller;

import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService iTransactionService;

    @PostMapping("/sendusd")
    @Transactional
    public ResponseEntity<?> sendUsd(@RequestParam long accountFromId,
                                     @RequestParam long userId,
                                     @RequestParam double amount,
                                     @RequestParam long accountToId,
                                     @RequestParam Type type) throws Exception {

        return ResponseEntity.ok().body(iTransactionService.sendUsd(accountFromId, userId, amount, accountToId, type));
    }

}
