package cn.itcast.order.service.impl;

import cn.itcast.order.common.OrderStatus;
import cn.itcast.order.domain.Order;
import cn.itcast.order.domain.PurchaseRecord;
import cn.itcast.order.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void generateOrder(String buyerId, List<String> goodsId) {

    }

    @Override
    public List<Order> findOrderByUserAndStatus(String userId, OrderStatus status) {
        return null;
    }

    @Override
    public List<PurchaseRecord> findRecordByOrder(Integer orderId) {
        return null;
    }

    @Override
    public void deliverGoodsOfOrder(Integer orderId) {

    }
}
