package cn.itcast.user.web;


import cn.itcast.feign.clients.ShopClient;
import cn.itcast.feign.common.GoodSearchData;
import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class GoodController {
    @Autowired
    private ShopClient shopClient;

    @PostMapping("/userSearchForSaleGood")
    public Result userSearchForSaleGood(@RequestBody GoodSearchData searchData) {
        try{
            MyPage<Goods> goodsPage = shopClient.SearchForSaleGood(searchData);
            return Result.succ(goodsPage,"搜索商品成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"搜索商品失败\n"+e.getMessage());
        }
    }
}
