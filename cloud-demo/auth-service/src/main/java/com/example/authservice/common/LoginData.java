package com.example.authservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {
    String ID;
    String password;
}
