package com.example.authservice.service;

import com.example.authservice.domain.AccountEntity;
import com.example.authservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity login(String id, String password) throws Exception{
        AccountEntity account= accountRepository.findByID(id);
        System.out.println("password="+password+", account.password="+account.getPassword());
        if(password==null || !password.equals(account.getPassword())){
            throw new Exception();
        }
        return account;
    }

}


