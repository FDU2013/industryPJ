package cn.itcast.feign.clients;


import cn.itcast.feign.domain.AddUserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userservice")
public interface UserClient {

    @PostMapping("/user/addUser")
    public boolean addUser(@RequestBody AddUserData user);



}
