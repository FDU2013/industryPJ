package cn.itcast.feign.clients;

import cn.itcast.feign.common.*;
import cn.itcast.feign.domain.Goods;
import cn.itcast.feign.domain.OrderToSee;
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

    @PostMapping("/shop/generateOrder")
    public List<String> generateOrder(@RequestBody UserOrderData userOrderData);

    @PostMapping("/shop/immediateOrder")
    public List<String> immediateOrder(@RequestBody IDGoodNumData idGoodNumData);

    @PostMapping("/shop/searchToPaidOrder")
    public List<OrderToSee> searchToPaidOrder(@RequestBody String id) ;

    @PostMapping("/shop/searchUndeliverOrder")
    public List<OrderToSee> searchUndeliverOrder(@RequestBody String id) ;


    @PostMapping("/shop/searchUnreceiveOrder")
    public List<OrderToSee> searchUnreceiveOrder(@RequestBody String id) ;

    @PostMapping("/shop/searchFinishOrder")
    public List<OrderToSee> searchFinishOrder(@RequestBody String id) ;

    @PostMapping("/shop/orderDetail")
    public List<GoodInfoNumData> orderDetail(@RequestBody Long orderID);

    @PostMapping("/shop/pay")
    public boolean pay(@RequestBody PayData payData);
}
