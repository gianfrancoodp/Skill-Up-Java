package com.alkemy.wallet.model;

import com.alkemy.wallet.util.CurrencyEnum;
import lombok.Data;
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
@Table(name = "ACCOUNTS")
@Data
@SQLDelete(sql = "UPDATE ACCOUNTS SET deleted = true WHERE ACCOUNT_ID=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY" , nullable = false)
    private CurrencyEnum currency;

    @Column(name = "TRANSACTION_LIMIT", nullable = false)
    private Double transactionLimit;

    @Column(name= "BALANCE" , nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false, nullable = false)
    private User userId;

    @Column(name = "CREATED_DATE", updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name = "UPDATE_DATE")
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