package cn.itcast.order.web;


import cn.itcast.feign.common.MyPage;
import cn.itcast.feign.common.PageSearchData;
import cn.itcast.feign.common.Result;
import cn.itcast.feign.util.MyPageTool;
import cn.itcast.order.common.*;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.domain.PurchaseRecord;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class CommentController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/getNeedComment")
    public Result getNeedComment(@RequestBody PageSearchData pageSearchData, HttpServletRequest request) {
        try {
            String ID = request.getHeader("ID");
            List<UserCommentData> userCommentDatas = orderService.getCommentByUserAndStatus(ID, CommentStatus.Pending);
            List<UserCommentDataForShow> ret = new ArrayList<>();
            for(UserCommentData commentData : userCommentDatas){
                ret.add(commentData.forShow());
            }
            MyPage<UserCommentDataForShow> page = MyPageTool.getPage(ret, pageSearchData.getPageSize(), pageSearchData.getPageNum());
            return Result.succ(page,"成功获得评价信息");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(342,"获取评价信息失败\n"+e.getMessage());
        }

    }

    @PostMapping("/getHaveCommented")
    public Result getHaveCommented(@RequestBody PageSearchData pageSearchData, HttpServletRequest request) {
        try {
            String ID = request.getHeader("ID");
            List<UserCommentData> userCommentDatas = orderService.getCommentByUserAndStatus(ID, CommentStatus.Filled);
            List<UserCommentDataForShow> ret = new ArrayList<>();
            for(UserCommentData commentData : userCommentDatas){
                ret.add(commentData.forShow());
            }
            MyPage<UserCommentDataForShow> page = MyPageTool.getPage(ret, pageSearchData.getPageSize(), pageSearchData.getPageNum());
            return Result.succ(page,"成功获得评价信息");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(342,"获取评价信息失败\n"+e.getMessage());
        }

    }

    @PostMapping("/toComment")
    public Result toComment(@RequestBody CommentData commentData, HttpServletRequest request) {
        try {
            orderService.comment(
                    commentData.getPurchaseID(),
                    commentData.getContent(),
                    commentData.getStars()
            );
            return Result.succ(null,"评价成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(342,"评价失败\n"+e.getMessage());
        }
    }

//    @PostMapping("/getGoodComment")
//    public Result getGoodComment(@RequestBody String goodID, HttpServletRequest request) {
//        try {
//            List<GoodInfoNumData> ret = new ArrayList<>();
//            List<PurchaseRecord> records = orderService.findRecordByOrder(orderID);
//            for(PurchaseRecord record : records){
//                String goodID = record.getGoodsId();
//                Goods good = goodService.findGoodsById(goodID);
//                GoodInfoNumData info = GoodInfoNumData.getPair(good,record.getGoodsNum());
//                info.setPrice(record.getTotalPrice());
//                ret.add(info);
//            }
//            return Result.succ(ret,"获得订单详情成功");
//        }catch (Exception e){
//            e.printStackTrace();
//            return Result.fail(342,"获取订单详情失败\n"+e.getMessage());
//        }
//
//    }
}
