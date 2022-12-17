package com.example.authservice.web;

import com.example.authservice.common.LoginData;
import com.example.authservice.common.Result;
import com.example.authservice.pojo.Account;
import com.example.authservice.domain.AccountEntity;
import com.example.authservice.pojo.AuthResponse;
import com.example.authservice.pojo.AuthResponseCode;
import com.example.authservice.service.AccountService;
import com.example.authservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

//    @Autowired
//    RedisUtil redisUtil;

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginData loginData) {
        AccountEntity accountEntity=null;
        //验证username + password

        try{
            accountEntity = accountService.login(loginData.getID(),loginData.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(AuthResponseCode.INCORRECT_PWD.intValue(),"登录失败");
        }

        Account account = Account.builder()
                .username(loginData.getID())
                .role(accountEntity.getRole())
                .build();

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        return Result.succ(account,"登录成功");
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(@RequestBody LoginData loginData) {
        //TODO
        AccountEntity accountEntity=null;
        //验证username + password

        try{
            accountEntity = accountService.login(loginData.getID(),loginData.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(AuthResponseCode.INCORRECT_PWD.intValue(),"登录失败");
        }

        Account account = Account.builder()
                .username(loginData.getID())
                .role(accountEntity.getRole())
                .build();

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        return Result.succ(account,"登录成功");
    }


    @GetMapping("/verify")
    public AuthResponse verify(@RequestParam String token,
                               @RequestParam String ID) {
        System.out.println("开始认证");
        boolean success = jwtService.verify(token, ID);
        System.out.println(success?"成功" : "失败");
        return AuthResponse.builder()
                // TODO 此处最好用invalid token之类的错误信息
                .code(success ? AuthResponseCode.SUCCESS : AuthResponseCode.USER_NOT_FOUND)
                .msg(success ? "认证成功" : "认证失败")
                .build();
    }
}
