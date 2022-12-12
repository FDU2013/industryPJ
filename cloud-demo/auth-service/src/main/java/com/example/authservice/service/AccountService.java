package com.example.authservice.service;

import com.example.authservice.mapper.AccountEntityMapper;
import com.example.authservice.pojo.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    private AccountEntityMapper accountMapper;

    public AccountEntity login(String username, String password) throws Exception{
        AccountEntity account= accountMapper.findByName(username);
        System.out.println("password="+password+", account.password="+account.getPassword());
        if(password==null || !password.equals(account.getPassword())){
            throw new Exception();
        }
        return account;
    }

}


