package cn.itcast.user.web;

import cn.itcast.feign.common.Result;
import cn.itcast.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserManageService userManageService;



    @PostMapping("/updateInfo")
    public Result queryById(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }
}
