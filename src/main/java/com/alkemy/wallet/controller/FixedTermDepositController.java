package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.FixedTermDepositDTO;
import com.alkemy.wallet.service.IFixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fixedDeposit")
public class FixedTermDepositController {

    /*
    @Autowired
    private IFixedTermDepositService fixedTermDepositService;


    TODO: Terminar este Endpoint para el siguiente Feature de "Inversiones"
    @PostMapping
    public ResponseEntity<FixedTermDepositDTO> save(@RequestBody FixedTermDepositDTO dto) {
        FixedTermDepositDTO newFixedTermDeposit = fixedTermDepositService.save(dto);
        return new ResponseEntity.status(HttpStatus.CREATED).body(newFixedTermDeposit);
    }
     */
}
