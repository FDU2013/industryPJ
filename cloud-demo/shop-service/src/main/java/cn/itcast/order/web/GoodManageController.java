package cn.itcast.order.web;


import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.common.Result;
import cn.itcast.order.common.*;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.service.GoodService;
import cn.itcast.feign.util.MyPageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shop")
public class GoodManageController {
    @Autowired
    private GoodService goodService;


    @PostMapping("/updateGood")
    public Result updateGood(@RequestBody Goods good) {
        try{
            System.out.println(good.getStatus().toString());
            goodService.updateGoods(good);
            return Result.succ(good.getName(),"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }

    }

    @PostMapping("/addGood")
    public Result addGood(@RequestBody AddGoodData good) {
        try{
            goodService.addGoods(good.ToGoods());
            return Result.succ(good.getName(),"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/adminSearchForSaleGood")
    public Result adminSearchForSaleGood(@RequestBody GoodSearchData searchData) {
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

    @PostMapping("/SearchForSaleGood")
    public MyPage<Goods> SearchForSaleGood(@RequestBody GoodSearchData searchData) {
        try{
            List<Goods> goods = goodService.searchGoodsByMediumAndTagAndStatus(
                    searchData.getMedium(),
                    searchData.getTag(),
                    searchData.getSearch(),
                    GoodsStatus.ForSale
            );
            MyPage<Goods> goodPage = MyPageTool.getPage(goods,searchData.getPageSize(),searchData.getPageNum());
            return goodPage;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/adminSearchSoldOutGood")
    public Result adminSearchSoldOutGood(@RequestBody GoodSearchData searchData) {
        try{
            List<Goods> goods = goodService.searchGoodsByMediumAndTagAndStatus(
                    searchData.getMedium(),
                    searchData.getTag(),
                    searchData.getSearch(),
                    GoodsStatus.SoldOut
            );
            MyPage<Goods> goodPage = MyPageTool.getPage(goods,searchData.getPageSize(),searchData.getPageNum());
            return Result.succ(goodPage);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(302,e.getMessage());
        }
    }

    @PostMapping("/entendGoodInfo")
    public List<GoodInfoNumData> entendGoodInfo(@RequestBody HashMap<String, Integer> shoplist) {
        try{
            List<GoodInfoNumData> list = new ArrayList<>();
            Set<String> goodIDs = shoplist.keySet();
            for (String goodID:goodIDs){
                Goods good = goodService.findGoodsById(goodID);
                list.add(GoodInfoNumData.getPair(good,shoplist.get(goodID)));
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
