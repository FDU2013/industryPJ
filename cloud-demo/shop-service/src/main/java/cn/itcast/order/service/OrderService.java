package cn.itcast.order.service;

import cn.itcast.order.common.OrderStatus;
import cn.itcast.order.domain.Order;
import cn.itcast.order.domain.PurchaseRecord;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    List<String> generateOrder(String buyerId, HashMap<String, Integer> goodsIdAndNum) throws Exception;
    List<Order> findOrderByUserAndStatus(String userId, OrderStatus status);
    List<PurchaseRecord> findRecordByOrder(Long orderId) throws Exception;
    void deliverGoodsOfOrder(Long orderId) throws Exception;
    void confirmReceipt(Long orderId) throws Exception;
    void comment(Long recordId, String content, Integer stars) throws Exception;

}
