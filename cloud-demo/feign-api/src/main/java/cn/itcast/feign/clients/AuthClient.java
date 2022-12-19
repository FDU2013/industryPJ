package cn.itcast.feign.clients;

import cn.itcast.feign.common.LoginData;
import cn.itcast.feign.pojo.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("authservice")
public interface AuthClient {


    @GetMapping("/auth/verify")
    public AuthResponse verify(@RequestParam("token") String token,
                               @RequestParam("ID") String ID);
    @PostMapping("/auth/changePassword")
    public boolean changePassword(@RequestBody Map<String,String> map);
}
