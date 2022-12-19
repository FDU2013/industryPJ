package cn.itcast.user.web;


import cn.itcast.feign.common.Result;
import cn.itcast.user.common.AddUserData;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody AddUserData user){
        try{
            userService.addUser(user.ToUser());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/info")
    public Result info(HttpServletRequest request) {
        //TODO
        return Result.fail(300,"300");
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(HttpServletRequest request) {
        //TODO
        return Result.fail(300,"300");
    }


}
