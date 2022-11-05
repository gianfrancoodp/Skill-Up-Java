package com.alkemy.wallet.dto;

import lombok.Data;

@Data
public class FixedTermDepositDTO {

    private long id;
    private double amount;
    private long userId;
    private long accountId;
    private double interest;
    private String creationDate;
    private String closingDate;

}