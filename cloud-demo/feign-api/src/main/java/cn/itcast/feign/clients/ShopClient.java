package cn.itcast.feign.clients;

import cn.itcast.feign.common.GoodInfoNumData;
import cn.itcast.feign.common.GoodSearchData;
import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.domain.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.HashMap;
import java.util.List;

@FeignClient("shopservice")
public interface ShopClient {
    @PostMapping("/shop/entendGoodInfo")
    public List<GoodInfoNumData> entendGoodInfo(@RequestBody HashMap<String, Integer> shoplist);

    @PostMapping("/shop/SearchForSaleGood")
    public MyPage<Goods> SearchForSaleGood(@RequestBody GoodSearchData searchData);



}
