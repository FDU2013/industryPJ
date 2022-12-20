package cn.itcast.user.web;


import cn.itcast.feign.clients.MoneyClient;
import cn.itcast.feign.clients.ShopClient;
import cn.itcast.feign.common.GoodInfoNumData;
import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.common.PageSearchData;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.domain.OrderToSee;
import cn.itcast.feign.util.MyPageTool;
import cn.itcast.feign.common.PayData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class OrderController {
    @Autowired
    private ShopClient shopClient;
    @Autowired
    private MoneyClient moneyClient;


    @PostMapping("/getToPaidOrder")
    public Result getToPaidOrder(@RequestBody PageSearchData pageSearchData, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        Map<String,Object> map = new HashMap<>();
        try{
            List<OrderToSee> orders = shopClient.searchToPaidOrder(ID);
            MyPage<OrderToSee> orderPage = MyPageTool.getPage(orders, pageSearchData.getPageSize(), pageSearchData.getPageNum());
            return Result.succ(orderPage,"获得待支付订单成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"获得待支付订单失败\n"+e.getMessage());
        }
    }

    @PostMapping("/getUndeliverOrder")
    public Result getUndeliverOrder(@RequestBody PageSearchData pageSearchData, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        Map<String,Object> map = new HashMap<>();
        try{
            List<OrderToSee> orders = shopClient.searchUndeliverOrder(ID);
            MyPage<OrderToSee> orderPage = MyPageTool.getPage(orders, pageSearchData.getPageSize(), pageSearchData.getPageNum());
            return Result.succ(orderPage,"获得待发货订单成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"获得待发货订单失败\n"+e.getMessage());
        }
    }

    @PostMapping("/getNotReceivedOrder")
    public Result getNotReceivedOrder(@RequestBody PageSearchData pageSearchData, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        Map<String,Object> map = new HashMap<>();
        try{
            List<OrderToSee> orders = shopClient.searchUnreceiveOrder(ID);
            MyPage<OrderToSee> orderPage = MyPageTool.getPage(orders, pageSearchData.getPageSize(), pageSearchData.getPageNum());
            return Result.succ(orderPage,"获得待收货订单成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"获得待收货订单失败\n"+e.getMessage());
        }
    }

    @PostMapping("/getFinishedOrder")
    public Result getFinishedOrder(@RequestBody PageSearchData pageSearchData, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        Map<String,Object> map = new HashMap<>();
        try{
            List<OrderToSee> orders = shopClient.searchFinishOrder(ID);
            MyPage<OrderToSee> orderPage = MyPageTool.getPage(orders, pageSearchData.getPageSize(), pageSearchData.getPageNum());
            return Result.succ(orderPage,"获得已完成订单成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"获得已完成订单失败\n"+e.getMessage());
        }
    }

    @PostMapping("/orderDetail")
    public Result orderDetail(@RequestBody Long orderID, HttpServletRequest request) {

        try{
            List<GoodInfoNumData> infos = shopClient.orderDetail(orderID);
            if(infos==null)throw new Exception();
            return Result.succ(infos,"获得订单详情成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"获得订单详情失败\n"+e.getMessage());
        }
    }

    @PostMapping("/pay")
    public Result pay(@RequestBody PayData payData, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            payData.setUserID(ID);
            if(!shopClient.pay(payData))throw new Exception("余额不足");
            return Result.succ(null,"支付成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(330,"支付失败\n"+e.getMessage());
        }
    }
}
