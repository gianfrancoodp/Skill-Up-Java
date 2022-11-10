package com.alkemy.wallet.dto;

import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.util.Type;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
public class TransactionDto {

    private long id;
    private double amount;
    private Type type;
    private String description;
    private Timestamp transactionDate;
    private String userFrom;
    private String account;


    public TransactionDto(Transaction transaction){
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.description = transaction.getDescription();
        this.userFrom = transaction.getUser().getLastName() + ", " + transaction.getUser().getFirstName();
        this.transactionDate = transaction.getTransactionDate();
        this.account = transaction.getAccount().getAccountId().toString();
    }
}
