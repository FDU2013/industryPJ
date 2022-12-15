package cn.itcast.order.web;


import cn.itcast.feign.common.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class GoodManageController {



    @PostMapping("/updateGood")
    public Result updateGood(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/addGood")
    public Result addGood(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/searchGood")
    public Result searchGood(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

}
