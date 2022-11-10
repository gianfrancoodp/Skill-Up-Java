package com.alkemy.wallet.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "FIXED_TERM_DEPOSITS")
@Data
public class FixedTermDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FIXED_TERM_DEPOSIT_ID", nullable = false)
    private long id;

    @Column(name = "AMOUNT", nullable = false )
    private double amount;

    @ManyToOne
    @JoinColumn(name = "USER_ID",insertable = false,updatable = false)

    private UserEntity userEntity;


    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID",insertable = false,updatable = false)
    private Account account;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Column(name = "INTEREST", nullable = false)
    private double interest = 0.5;

    @CreationTimestamp
    @Column(name = "CREATION_DATE", updatable=false)
    private Timestamp creationDate;

    @CreationTimestamp
    @Column(name = "CLOSING_DATE", updatable=false)
    private Timestamp closingDate;

    // Default Constructor
    public FixedTermDeposit(){
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }

    // Custom Constructor
    public FixedTermDeposit(long id, double amount, Long userId, Long accountId, Timestamp closingDate) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.accountId = accountId;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
        this.closingDate = closingDate;
    }
}