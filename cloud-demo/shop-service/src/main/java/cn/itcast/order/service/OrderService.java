package cn.itcast.order.service;

import cn.itcast.feign.common.OrderStatus;
import cn.itcast.order.common.CommentStatus;
import cn.itcast.order.common.GoodsCommentData;
import cn.itcast.order.common.UserCommentData;
import cn.itcast.order.domain.Comment;
import cn.itcast.order.domain.Order;
import cn.itcast.order.domain.PurchaseRecord;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    List<String> generateOrder(String buyerId, HashMap<String, Integer> goodsIdAndNum) throws Exception;
    void purchaseOrder(Long orderId, String address, String notes) throws Exception;
    List<Order> findOrderByUserAndStatus(String userId, OrderStatus status);
    List<Order> findOrderByStatus(OrderStatus status);
    List<PurchaseRecord> findRecordByOrder(Long orderId) throws Exception;
    void deliverGoodsOfOrder(Long orderId, String waybillNum) throws Exception;
    void confirmReceipt(Long orderId) throws Exception;
    void comment(Long recordId, String content, Integer stars) throws Exception;
    Order findOrderByID(Long orderID) throws Exception;
    List<UserCommentData> getCommentByUserAndStatus(String userId, CommentStatus status);
    List<GoodsCommentData> getCommentByGoods(String goodsId);
}
