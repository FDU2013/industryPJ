package cn.itcast.order.web;


import cn.itcast.feign.clients.MoneyClient;
import cn.itcast.feign.common.*;
import cn.itcast.order.common.GoodInfoNumData;
import cn.itcast.order.common.OrderToSee;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.domain.Order;
import cn.itcast.order.domain.PurchaseRecord;
import cn.itcast.order.service.GoodService;
import cn.itcast.order.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private MoneyClient moneyClient;

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

    @PostMapping("/searchToPaidOrder")
    public List<OrderToSee> searchToPaidOrder(@RequestBody String id) {
        List<Order> orders = orderService.findOrderByUserAndStatus(id, OrderStatus.NotPaid);
        return OrderToSee.getToSee(orders);
    }

    @PostMapping("/searchUndeliverOrder")
    public List<OrderToSee> searchUndeliverOrder(@RequestBody String id) {
        List<Order> orders = orderService.findOrderByUserAndStatus(id, OrderStatus.NotYetShipped);
        return OrderToSee.getToSee(orders);
    }



    @PostMapping("/searchUnreceiveOrder")
    public List<OrderToSee> searchUnreceiveOrder(@RequestBody String id) {
        List<Order> orders = orderService.findOrderByUserAndStatus(id, OrderStatus.Shipped);
        return OrderToSee.getToSee(orders);
    }

    @PostMapping("/searchFinishOrder")
    public List<OrderToSee> searchFinishOrder(@RequestBody String id) {
        List<Order> orders = orderService.findOrderByUserAndStatus(id, OrderStatus.Received);
        return OrderToSee.getToSee(orders);
    }

    @PostMapping("/orderDetail")
    public List<GoodInfoNumData> orderDetail(@RequestBody Long orderID) {
        try {
            List<GoodInfoNumData> ret = new ArrayList<>();
            List<PurchaseRecord> records = orderService.findRecordByOrder(orderID);
            for(PurchaseRecord record : records){
                String goodID = record.getGoodsId();
                Goods good = goodService.findGoodsById(goodID);
                GoodInfoNumData info = GoodInfoNumData.getPair(good,record.getGoodsNum());
                info.setPrice(record.getTotalPrice());
                ret.add(info);
            }
            return ret;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/pay")
    public boolean pay(@RequestBody PayData payData) {
        try {
            Order order = orderService.findOrderByID(payData.getOrderID());
            BigDecimal need = order.getTotalPrice();
            UserMoneyData umd = new UserMoneyData(payData.getUserID(), need);
            if(!moneyClient.isEnough(umd))throw new Exception();
            orderService.purchaseOrder(payData.getOrderID(),payData.getAddress(),payData.getNotes());
            moneyClient.decrease(umd);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
