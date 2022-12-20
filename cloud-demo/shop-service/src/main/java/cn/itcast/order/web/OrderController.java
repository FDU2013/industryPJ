package cn.itcast.order.web;


import cn.itcast.feign.common.IDGoodNumData;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.common.UserOrderData;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/generateOrder")
    public List<String> generateOrder(@RequestBody UserOrderData userOrderData) {
        try {
            List<String> SoldOutGood = orderService.generateOrder(userOrderData.getBuyerId(),userOrderData.getGoodsIdAndNum());
            return SoldOutGood;
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/immediateOrder")
    public List<String> immediateOrder(@RequestBody IDGoodNumData idGoodNumData) {
        try {
            HashMap<String, Integer> pairs = new HashMap<>();
            pairs.put(idGoodNumData.getGoodID(), idGoodNumData.getNum());
            List<String> SoldOutGood = orderService.generateOrder(idGoodNumData.getID(), pairs);
            return SoldOutGood;
        }catch (Exception e){
            return null;
        }
    }

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
