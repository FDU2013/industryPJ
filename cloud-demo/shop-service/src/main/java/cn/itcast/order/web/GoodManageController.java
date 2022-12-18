package cn.itcast.order.web;


import cn.itcast.feign.common.Result;
import cn.itcast.order.common.GoodSearchData;
import cn.itcast.order.common.GoodsStatus;
import cn.itcast.order.common.MyPage;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.service.GoodService;
import cn.itcast.order.util.MyPageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class GoodManageController {
    @Autowired
    private GoodService goodService;


    @PostMapping("/updateGood")
    public Result updateGood(@RequestBody Goods good) {
        try{
            goodService.updateGoods(good);
            return Result.succ(good.getName(),"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }

    }

    @PostMapping("/addGood")
    public Result addGood(@RequestBody Goods good) {
        try{
            goodService.addGoods(good);
            return Result.succ(good.getName(),"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/searchGood")
    public Result searchGood(@RequestBody GoodSearchData searchData) {
        try{
            List<Goods> goods = goodService.searchGoodsByMediumAndTagAndStatus(
                    searchData.getMedium(),
                    searchData.getTag(),
                    searchData.getSearch(),
                    GoodsStatus.ForSale
            );
            MyPage<Goods> goodPage = MyPageTool.getPage(goods,searchData.getPageSize(),searchData.getPageNum());
            return Result.succ(goodPage);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(302,e.getMessage());
        }
    }

}
