package com.alkemy.wallet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="ROLES")
public class Role {

    @Id
    @Column(name="ID")
    private Long id;
    @Column(name="NAME")
    @NotNull
    private String name;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="CREATION_DATE")
    private Timestamp creationDate;
    @Column(name="UPDATE_DATE")
    private Timestamp updateDate;

    public Role(){
    super();
    }

    public Role(String name, String description){
        this.name= name;
        this.description= description;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    enum DefineRole {
        ADMIN,
        USER
    }
}
