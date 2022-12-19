package com.example.authservice.service;

import cn.itcast.feign.clients.MoneyClient;
import cn.itcast.feign.clients.UserClient;
import com.example.authservice.common.RegisterData;
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

    public AccountEntity addUserAccount(RegisterData registerData)throws Exception{
        AccountEntity account= accountRepository.findByID(registerData.getID());
        if(account!=null){
            throw new Exception("ID重复");
        }

        if(registerData.getPassword()==null){
            throw new Exception("密码不可为空");
        }
        accountRepository.save(new AccountEntity(registerData.getID(),registerData.getPassword(),"user"));
        return account;
    }

}


