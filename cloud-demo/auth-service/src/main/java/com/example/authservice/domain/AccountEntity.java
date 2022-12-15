package com.example.authservice.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @Column(name = "ID", unique = true)
    String ID;

    @Column(name = "password")
    String password;


    @Column(name = "role")
    String role;
}
