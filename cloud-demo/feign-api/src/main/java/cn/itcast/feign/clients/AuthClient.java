package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("authservice")
public interface AuthClient {


    @GetMapping("/verify")
    public AuthResponse verify(@RequestParam("token") String token,
                               @RequestParam("username") String username);

    @PostMapping("/refresh")
    @ResponseBody
    public AuthResponse refresh(@RequestParam("refresh") String refresh);

}
