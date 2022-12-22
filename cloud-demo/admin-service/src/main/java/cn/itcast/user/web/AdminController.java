package cn.itcast.user.web;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.common.PageSearchData;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.common.UserToSee;
import cn.itcast.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserClient userClient;



    @PostMapping("/getUserPage")
    public Result queryById(@RequestBody PageSearchData pageSearchData) {
        MyPage<UserToSee> page = userClient.getUserPage(pageSearchData);
        return Result.succ(page);
    }

}
