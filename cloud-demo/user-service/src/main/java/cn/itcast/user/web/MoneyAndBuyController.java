package cn.itcast.user.web;


import cn.itcast.feign.clients.MoneyClient;
import cn.itcast.feign.clients.ShopClient;
import cn.itcast.feign.common.Result;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class MoneyAndBuyController {
    @Autowired
    private MoneyClient moneyClient;
    @Autowired
    private ShopClient shopClient;
    @Autowired
    private UserService userService;

    @PostMapping("/buy")
    public Result buy(HttpServletRequest request) {
        //TODO 参数还需要一个结构体
        return Result.fail(300,"300");
    }

    @PostMapping("/buyShoplist")
    public Result buyShoplist(@RequestBody String deleLocation, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            userService.purchaseAllCart(ID);
            BigDecimal money = moneyClient.getMoney(ID);
            return  Result.succ(money,"获得余额成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(310,e.getMessage());
        }
    }

    @PostMapping("/getMoney")
    public Result getMoney(HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            BigDecimal money = moneyClient.getMoney(ID);
            return  Result.succ(money,"获得余额成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(310,e.getMessage());
        }
    }
}
