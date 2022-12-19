package com.example.authservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class RegisterData {
    String ID;
    String password;
    private String name;
    private String email;
    private String phone;
}
