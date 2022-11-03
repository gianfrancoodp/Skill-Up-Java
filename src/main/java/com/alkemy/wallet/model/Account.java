package com.alkemy.wallet.model;

import com.alkemy.wallet.util.CurrencyEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@SQLDelete(sql = "UPDATE accounts SET deleted = true WHERE account_id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyEnum currency;

    @Column(name = "transaction_limit", nullable = false)
    private Double transactionLimit;

    @Column(nullable = false)
    private Double balance;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "created_date", updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    private boolean deleted = Boolean.FALSE;


    public Account(CurrencyEnum currency, Double transactionLimit, Double balance, User userId, Timestamp creationDate) {
        this.currency = currency;
        this.transactionLimit = transactionLimit;
        this.balance = balance;
        this.userId = userId;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }
}