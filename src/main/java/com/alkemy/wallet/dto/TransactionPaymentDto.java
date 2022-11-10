package com.alkemy.wallet.dto;

import javax.validation.constraints.NotNull;

public class TransactionPaymentDto {

    private double amount;

    @NotNull(message = "You must provide a description for this transaction.")
    private String description;

    @NotNull(message = "You must provide an account number.")
    private Long accountId;

    private Long userId;
}
