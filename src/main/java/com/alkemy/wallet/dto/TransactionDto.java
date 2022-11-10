package com.alkemy.wallet.dto;

import com.alkemy.wallet.util.Type;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransactionDto {
    private long id;
    private Double amount;
    private Type type;
    private String description;
    private Timestamp transactionDate;

}
