package com.alkemy.wallet.model;

<<<<<<< HEAD
import lombok.Data;

=======
import lombok.NoArgsConstructor;
>>>>>>> feature/3-userAll

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

<<<<<<< HEAD
@Entity
@Data
@Table(name="USERS")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

=======

@Table(name="USERS")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
>>>>>>> feature/3-userAll
public class User {
    @Id
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
    private Role roleId;



    @Column(name = "CREATED_DATE", updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;
    @UpdateTimestamp
    private Timestamp updateDate;


    private boolean deleted = Boolean.FALSE;


<<<<<<< HEAD
    public User() {
=======
    public User(String firstName, String lastName, String email, String password) {
>>>>>>> feature/3-userAll
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }
<<<<<<< HEAD
}
=======
}

>>>>>>> feature/3-userAll
