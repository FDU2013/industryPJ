package cn.itcast.user.web;


import cn.itcast.feign.clients.MoneyClient;
import cn.itcast.feign.clients.ShopClient;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.common.UserOrderData;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

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
    public Result buyShoplist(HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            HashMap<String, Integer> pairs = userService.purchaseAllCart(ID);
            List<String> SoldOutGoods = shopClient.generateOrder(new UserOrderData(ID,pairs));
            if(SoldOutGoods==null)throw new Exception("购物车中全为下架商品!\n已自动清空购物车");
            if(SoldOutGoods.isEmpty()){
                return  Result.succ(ID,"生成订单成功");
            }
            return new Result(201,"部分商品下架，已自动删除",SoldOutGoods);

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
