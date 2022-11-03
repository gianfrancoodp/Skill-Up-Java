package com.alkemy.wallet.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "fixed_term_deposits")
@Data
public class FixedTermDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FIXED_TERM_DEPOSIT_ID", nullable = false)
    private long id;

    @Column(name = "AMOUNT", nullable = false )
    private double amount;

    //@ManyToOne
    //@JoinTable(name = "users_fixed_term_deposits", joinColumns = @JoinColumn(name = "FIXED_TERM_DEPOSIT_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "USER_ID", nullable = false)
    private long userId;

    //@ManyToOne
    //@JoinTable(name = "accounts_fixed_term_deposits", joinColumns = @JoinColumn(name = "FIXED_TERM_DEPOSIT_ID"), inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    @Column(name = "ACCOUNT_ID", nullable = false)
    private long accountId;

    @Column(name = "INTEREST", nullable = false)
    private double interest;

    @CreationTimestamp
    @Column(name = "CREATION_DATE", updatable=false)
    private Timestamp creationDate;

    @CreationTimestamp
    @Column(name = "CLOSING_DATE", updatable=false)
    private Timestamp closingDate;

}