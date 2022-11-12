package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.FixedTermDepositDto;
import com.alkemy.wallet.dto.basicDTO.FixedTermDepositDtoSimulation;
import com.alkemy.wallet.service.IFixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fixedDeposit")
public class FixedTermDepositController {

    @Autowired
    private IFixedTermDepositService fixedTermDepositService;

    @PostMapping
    public ResponseEntity<FixedTermDepositDto> save(@RequestBody FixedTermDepositDto dto) throws Exception {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        FixedTermDepositDto newFixedTermDeposit = fixedTermDepositService.save(dto, userName);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFixedTermDeposit);
    }

    @PostMapping("/diffRoute")
    public ResponseEntity<String> creditFixedTermDeposit(@RequestBody long fixedTermDepositId) throws Exception {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String creditResult = fixedTermDepositService.creditFixedTermDeposit(fixedTermDepositId, userName);
        return ResponseEntity.status(HttpStatus.CREATED).body(creditResult);
    }

    @GetMapping("/simulate")
    public ResponseEntity<FixedTermDepositDtoSimulation> simulateFixedTermDeposit(@RequestBody FixedTermDepositDtoSimulation dto) throws Exception {
        FixedTermDepositDtoSimulation simulation = fixedTermDepositService.simulateFixedTermDeposit(dto);
        return ResponseEntity.status(HttpStatus.OK).body(simulation);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FixedTermDepositDto>> getAllFixedTermDeposits() throws Exception {
        List<FixedTermDepositDto> fixedTermDepositList = fixedTermDepositService.getAll();
        return ResponseEntity.ok().body(fixedTermDepositList);
    }

    @GetMapping("/{fixedTermDepositId}")
    public ResponseEntity<FixedTermDepositDto> getById(@PathVariable long fixedTermDepositId) throws Exception {
        FixedTermDepositDto result = fixedTermDepositService.getById(fixedTermDepositId);
        return ResponseEntity.ok().body(result);
    }
}