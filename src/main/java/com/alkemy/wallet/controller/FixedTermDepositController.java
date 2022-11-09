package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.FixedTermDepositDto;
import com.alkemy.wallet.repository.service.IFixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fixedDeposit")
public class FixedTermDepositController {

    @Autowired
    private IFixedTermDepositService fixedTermDepositService;

    @PostMapping
    public ResponseEntity<FixedTermDepositDto> save(@RequestBody FixedTermDepositDto dto) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        FixedTermDepositDto newFixedTermDeposit = fixedTermDepositService.save(dto, userName);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFixedTermDeposit);
    }
}