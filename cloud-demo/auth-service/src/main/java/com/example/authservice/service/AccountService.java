package com.example.authservice.service;

import com.example.authservice.mapper.AccountEntityMapper;
import com.example.authservice.pojo.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {
    @Autowired
    private static AccountEntityMapper accountMapper;

    public static AccountEntity login(String username, String password) throws Exception{
        AccountEntity account= accountMapper.findByName(username);
        if(password==null || !password.equals(account.getPassword())){
            throw new Exception();
        }
        return account;
    }

}


