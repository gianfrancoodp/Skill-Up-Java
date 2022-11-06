package com.alkemy.wallet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Table(name="USERS")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(nullable = false, unique=true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "id")
    private Long roleId;



    @Column(name = "CREATED_DATE", updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;
    @UpdateTimestamp
    private Timestamp updateDate;


    private boolean deleted = Boolean.FALSE;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }
}

