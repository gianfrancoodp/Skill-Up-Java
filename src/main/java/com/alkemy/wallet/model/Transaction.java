package com.alkemy.wallet.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name= "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private double amount;

    private enum type {
        income,
        payment,
        deposit
    }
    private String description;
    private Timestamp transactionDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    public User getUser() { return user;}
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    public Account getAccount() { return account;}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }
}
