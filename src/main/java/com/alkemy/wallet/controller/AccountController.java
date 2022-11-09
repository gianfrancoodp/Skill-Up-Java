package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.util.CurrencyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/account/{idUser}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable Long idUser , @RequestBody CurrencyEnum currency) throws Exception {
        return ResponseEntity.ok().body(accountService.createAccount(currency, idUser));
    }

    @GetMapping("/accounts/{idUser}")
    public ResponseEntity<List<AccountDto>> listAccounts(@PathVariable Long idUser) throws Exception{
        return ResponseEntity.ok().body(accountService.accountList(idUser));
    }

}
