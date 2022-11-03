package com.alkemy.wallet.model;


import com.alkemy.wallet.util.RoleEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="ROLES")
@Data
public class Role {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="NAME",nullable=false)
    @NotNull(message = "You must indicate a name to the Role: ADMIN or USER.")
    @Enumerated(EnumType.STRING)
    private RoleEnum.roleEnum name;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="CREATION_DATE",updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;
    @Column(name="UPDATE_DATE")
    @UpdateTimestamp
    private Timestamp updateDate;


    public Role(){
    super();
    this.creationDate= Timestamp.valueOf(LocalDateTime.now());
    }

    public Role(RoleEnum.roleEnum name, String description){
        this.name= name;
        this.description= description;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }




}
