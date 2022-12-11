package com.example.authservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Account {
    private String username;
    private String role;

    private String token;

    private String refreshToken;
}
