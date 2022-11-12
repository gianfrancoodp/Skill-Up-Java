package com.alkemy.wallet.dto.basicDTO;

import lombok.Data;

@Data
public class FixedTermDepositDtoSimulation {

    private double amount;
    private double interestEarned;
    private double totalEarned;
    private String creationDate;
    private String closingDate;

}