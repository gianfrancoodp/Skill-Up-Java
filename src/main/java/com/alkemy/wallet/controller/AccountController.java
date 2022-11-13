package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.util.CurrencyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private IAccountService accountService;

    @Autowired
    public AccountController(@Lazy IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/balance")
    public ResponseEntity<?> getAccountsBalance(@RequestParam UserEntity userId) throws Exception {
        return ResponseEntity.ok().body(accountService.getAccountsBalance(userId));
    }

    @PostMapping("/account/{idUser}")
    public ResponseEntity<AccountDto> createAccount(@PathVariable Long idUser, @RequestBody CurrencyEnum currency) throws Exception {
        return ResponseEntity.ok().body(accountService.createAccount(currency, idUser));
    }

    @GetMapping("/accounts/{idUser}")
    public ResponseEntity<List<AccountDto>> listAccounts(@PathVariable Long idUser) throws Exception {
        return ResponseEntity.ok().body(accountService.accountList(idUser));
    }



    @PatchMapping("/accounts/{idUser}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long idUser ,@RequestBody AccountDto accountDto) throws Exception {
        return ResponseEntity.ok().body(accountService.updateAccount(idUser,accountDto));
    }


    @GetMapping("/accounts")
    public ResponseEntity<PagedModel<AccountDto>> getAll(@RequestParam(required = false) Integer pageQuery){
        try{
            PagedModel<AccountDto> accountsDto = accountService.findAll(pageQuery);
            return new ResponseEntity<>(accountsDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

