package cn.itcast.user.web;

import cn.itcast.feign.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserAddressController {
    @PostMapping("/addAddress")
    public Result addAddress(HttpServletRequest request) {
        //TODO 参数还需要一个结构体
        return Result.fail(300,"300");
    }

    @PostMapping("/deleteAddress")
    public Result deleteAddress(@RequestBody String deleLocation, HttpServletRequest request) {
        //TODO 参数还需要删除掉的收获地址
        return Result.fail(300,"300");
    }

    @PostMapping("/allAddress")
    public Result allAddress(HttpServletRequest request) {
        //TODO 参数还需要删除掉的收获地址
        return Result.fail(300,"300");
    }
}
