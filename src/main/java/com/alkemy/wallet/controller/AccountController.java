package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<AccountDto> createAccount(Long idUser , @RequestBody AccountDto request){
        return ResponseEntity.ok().body(accountService.createAccount(request, idUser));
    }

    @GetMapping("/account/all")
    public ResponseEntity<List<AccountDto>> listAccounts(Long idUser){
        return ResponseEntity.ok().body(accountService.accountList(idUser));
    }

    @GetMapping("/account/balance")
    public ResponseEntity<String> myBalance(Long idUser){
        //method for return balance(ARS and USD) in account
        return ResponseEntity.ok().body(accountService.myBalance(idUser));
    }

}
