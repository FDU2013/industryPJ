package com.example.authservice.web;

import com.example.authservice.common.LoginData;
import com.example.authservice.common.Result;
import com.example.authservice.pojo.Account;
import com.example.authservice.pojo.AccountEntity;
import com.example.authservice.pojo.AuthResponse;
import com.example.authservice.pojo.AuthResponseCode;
import com.example.authservice.service.AccountService;
import com.example.authservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginData loginData) {
        AccountEntity accountEntity=null;
        //验证username + password
        //TODO
        if(loginData!=null){
            return Result.succ(new Account("admin","admin","aa","aa"),"登录成功");
        }

        try{
            accountEntity = accountService.login(loginData.getUsername(),loginData.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(AuthResponseCode.INCORRECT_PWD.intValue(),"登录失败");
        }

        Account account = Account.builder()
                .username(loginData.getUsername())
                .role(accountEntity.getRole())
                .build();

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return Result.succ(account,"登录成功");
    }

    @PostMapping("/refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam String refreshToken) {
        Account account = (Account) redisTemplate.opsForValue().get(refreshToken);
        if (account == null) {
            return AuthResponse.builder()
                    .code(AuthResponseCode.USER_NOT_FOUND)
                    .msg("刷新成功")
                    .build();
        }

        String jwt = jwtService.token(account);
        account.setToken(jwt);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.delete(refreshToken);
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .code(AuthResponseCode.SUCCESS)
                .msg("登录成功")
                .build();
    }

    @GetMapping("/verify")
    public AuthResponse verify(@RequestParam String token,
                               @RequestParam String username) {
        boolean success = jwtService.verify(token, username);
        return AuthResponse.builder()
                // TODO 此处最好用invalid token之类的错误信息
                .code(success ? AuthResponseCode.SUCCESS : AuthResponseCode.USER_NOT_FOUND)
                .msg(success ? "认证成功" : "认证失败")
                .build();
    }
}
