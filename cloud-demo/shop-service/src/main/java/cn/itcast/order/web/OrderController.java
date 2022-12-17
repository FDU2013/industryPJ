package cn.itcast.order.web;


import cn.itcast.feign.common.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class OrderController {

    @PostMapping("/searchUndeliverOrder")
    public Result searchUndeliverOrder(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/deliver")
    public Result deliver(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/searchUnreceiveOrder")
    public Result searchUnreceiveOrder(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

    @PostMapping("/searchFinishOrder")
    public Result searchFinishOrder(@PathVariable("id") Long id) {

        return Result.fail(300,"300");
    }

}
