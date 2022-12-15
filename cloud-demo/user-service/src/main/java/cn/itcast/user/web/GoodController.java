package cn.itcast.user.web;


import cn.itcast.feign.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class GoodController {
    @PostMapping("/searchGood")
    public Result searchGood(HttpServletRequest request) {
        //TODO 参数还需要删除掉的收获地址
        return Result.fail(300,"300");
    }
}
