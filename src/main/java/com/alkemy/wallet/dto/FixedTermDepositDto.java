package com.alkemy.wallet.dto;

import lombok.Data;

@Data
public class FixedTermDepositDto {

    private long id;
    private double amount;
    private long userId;
    private long accountId;
    private double interest;
    private String creationDate;
    private String closingDate;
    private boolean active;

}