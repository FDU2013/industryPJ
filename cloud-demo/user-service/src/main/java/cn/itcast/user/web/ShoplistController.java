package cn.itcast.user.web;


import cn.itcast.feign.clients.ShopClient;
import cn.itcast.feign.common.GoodInfoNumData;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.common.GoodNumData;
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
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ShoplistController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopClient shopClient;


    @PostMapping("/getShoplist")
    public Result getShoplist(HttpServletRequest request) {
        String ID = request.getHeader("ID");
        Map<String,Object> map = new HashMap<>();
        try{
            HashMap<String, Integer> pairs = userService.getCartOfUser(ID);
            List<GoodInfoNumData> infos = shopClient.entendGoodInfo(pairs);
            if(infos==null)throw new Exception("购物车中有不存在的商品!请联系管理员");
            BigDecimal totalPrice = new BigDecimal(0);
            for(GoodInfoNumData info:infos){
                totalPrice = totalPrice.add(info.getPrice().multiply(new BigDecimal(info.getNum())));
            }
            map.put("totalPrice",totalPrice);
            map.put("records",infos);
            return Result.succ(map,"获得购物车成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"获得购物车失败\n"+e.getMessage());
        }
    }

    @PostMapping("/addToShoplist")
    public Result addToShoplist(@RequestBody GoodNumData goodNumData, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            userService.addGoodsToCart(ID,goodNumData.getGoodID(), goodNumData.getNum());
            return Result.succ(null,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"添加购物车失败\n"+e.getMessage());
        }
    }

    @PostMapping("/updateShoplist")
    public Result updateShoplist(@RequestBody GoodNumData goodNumData,HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            userService.updateGoodsInCart(ID,goodNumData.getGoodID(), goodNumData.getNum());
            return Result.succ(null,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"修改失败\n"+e.getMessage());
        }
    }

    @PostMapping("/clearShoplist")
    public Result clearShoplist(HttpServletRequest request) {

        String ID = request.getHeader("ID");
        try{
            userService.emptyCartOfUser(ID);
            return Result.succ(null,"清空成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"清空购物车失败\n"+e.getMessage());
        }
    }
}
