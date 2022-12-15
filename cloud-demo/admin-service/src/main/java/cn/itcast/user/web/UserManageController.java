package cn.itcast.user.web;


import cn.itcast.feign.common.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserManageController {

    @PostMapping("/addUser")
    public Result addUser(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/updateUser")
    public Result updateUser(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/getAllUser")
    public Result getAllUser(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/addMoney")
    public Result addMoney(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }


}
