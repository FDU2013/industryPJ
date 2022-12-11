package com.example.authservice.pojo;

import lombok.Data;

@Data
public class AccountEntity {
    String username;
    String password;
    String role;
}
