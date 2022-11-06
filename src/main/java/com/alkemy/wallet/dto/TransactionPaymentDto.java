package com.alkemy.wallet.dto;

import javax.validation.constraints.NotNull;

public class TransactionPaymentDto {

    private double amount;

    @NotNull(message = "You must provide a description for this transaction.")
    private String description;

    private Long userId;
    @NotNull(message = "You must provide account number.")
    private Long accountId;
}
