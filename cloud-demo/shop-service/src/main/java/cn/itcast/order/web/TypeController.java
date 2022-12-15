package cn.itcast.order.web;

import cn.itcast.feign.common.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class TypeController {

    @PostMapping("/addType")
    public Result addType(@RequestBody Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/getAllType")
    public Result getAllType(@RequestBody Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/updateType")
    public Result updateType(@RequestBody Long id) {

        return Result.fail(300,"300");
    }
}
