package cn.itcast.feign.clients;


import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.common.PageSearchData;
import cn.itcast.feign.common.UserToSee;
import cn.itcast.feign.domain.AddUserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userservice")
public interface UserClient {

    @PostMapping("/user/addUser")
    public boolean addUser(@RequestBody AddUserData user);

    @PostMapping("/user/getUserPage")
    public MyPage<UserToSee> getUserPage(@RequestBody PageSearchData pageSearchData);



}
