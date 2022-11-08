package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.User;
import com.alkemy.wallet.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private IAccountService iAccountService;

    @GetMapping("/account/balance")
    public ResponseEntity<?> getAccountsBalance(@RequestParam User userId) throws Exception {
        return ResponseEntity.ok().body(iAccountService.getAccountsBalance(userId));
    }
}
