package cn.itcast.order.service;

import cn.itcast.order.common.OrderStatus;
import cn.itcast.order.domain.Order;
import cn.itcast.order.domain.PurchaseRecord;

import java.util.List;

public interface OrderService {
    void generateOrder(String buyerId, List<String> goodsId);
    List<Order> findOrderByUserAndStatus(String userId, OrderStatus status);
    List<PurchaseRecord> findRecordByOrder(Integer orderId);
    void deliverGoodsOfOrder(Integer orderId);

}
