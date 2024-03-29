package cn.itcast.order.service.impl;

import cn.itcast.order.common.CommentStatus;
import cn.itcast.order.common.GoodsCommentData;
import cn.itcast.order.common.GoodsStatus;
import cn.itcast.feign.common.OrderStatus;
import cn.itcast.order.common.UserCommentData;
import cn.itcast.order.domain.Comment;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.domain.Order;
import cn.itcast.order.domain.PurchaseRecord;
import cn.itcast.order.repository.*;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    GoodsRepository goodsRepository;
    OrderRepository orderRepository;
    PurchaseRecordRepository purchaseRecordRepository;
    CommentRepository commentRepository;
    @Autowired
    OrderServiceImpl(GoodsRepository goodsRepository, OrderRepository orderRepository,
                     PurchaseRecordRepository purchaseRecordRepository, CommentRepository commentRepository){
        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
        this.purchaseRecordRepository = purchaseRecordRepository;
        this.commentRepository = commentRepository;
    }
    @Override
    public List<String> generateOrder(String buyerId, HashMap<String, Integer> goodsIdAndNum) throws Exception {
        List<String> res = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        List<PurchaseRecord> records = new ArrayList<>();
        Order order = orderRepository.save(new Order(null, buyerId, null, OrderStatus.NotPaid, new ArrayList<>(),
                null, null, null));
        for(String id:goodsIdAndNum.keySet()){
            Goods goods = goodsRepository.findByGoodsId(id);
            if(goods.getStatus() == GoodsStatus.SoldOut){
                res.add(goods.getGoodsId());
                continue;
            }
            BigDecimal price = goods.getPrice();
            total = total.add(price.multiply(new BigDecimal(goodsIdAndNum.get(id))));
            Integer num = goodsIdAndNum.get(id);
            records.add(new PurchaseRecord(null, buyerId, goods.getGoodsId(), num, price.multiply(new BigDecimal(num)),
                    order, null));
        }
        if(records.size() == 0){
            orderRepository.deleteById(order.getId());
            throw new Exception("订单内商品全部下架，生成订单失败");
        }
        order.setTotalPrice(total);
        order.setRecords(records);
        orderRepository.save(order);
        return res;
    }

    @Override
    public void purchaseOrder(Long orderId, String address, String notes) throws Exception {
        Optional<Order> opOrder = orderRepository.findById(orderId);
        if(!opOrder.isPresent()){
            throw new Exception("订单号错误");
        }
        Order order = opOrder.get();
        if(order.getStatus() != OrderStatus.NotPaid){
            throw new Exception("订单已支付");
        }
        order.setStatus(OrderStatus.NotYetShipped);
        order.setAddress(address);
        order.setNotes(notes);
        orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderByUserAndStatus(String userId, OrderStatus status) {
        return orderRepository.findByBuyerIdAndStatus(userId, status);
    }

    @Override
    public List<Order> findOrderByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<PurchaseRecord> findRecordByOrder(Long orderId) throws Exception {
        Optional<Order> opOrder = orderRepository.findById(orderId);
        if(!opOrder.isPresent()){
            throw new Exception("订单号错误");
        }
        return opOrder.get().getRecords();
    }

    @Override
    public void deliverGoodsOfOrder(Long orderId, String waybillNum) throws Exception {
        Optional<Order> opOrder = orderRepository.findById(orderId);
        if(!opOrder.isPresent()){
            throw new Exception("订单号错误");
        }
        Order order = opOrder.get();
        if(order.getStatus() != OrderStatus.NotYetShipped){
            throw new Exception("该订单状态不是未发货");
        }
        order.setStatus(OrderStatus.Shipped);
        order.setWaybillNum(waybillNum);
        orderRepository.save(order);
    }

    @Override
    public void confirmReceipt(Long orderId) throws Exception {
        Optional<Order> opOrder = orderRepository.findById(orderId);
        if(!opOrder.isPresent()){
            throw new Exception("订单号错误");
        }
        Order order = opOrder.get();
        if(order.getStatus() != OrderStatus.Shipped){
            throw new Exception("该订单状态不是已发货");
        }
        order.setStatus(OrderStatus.Received);
        List<PurchaseRecord> records = order.getRecords();
        orderRepository.save(order);
        for(PurchaseRecord record:records){
            record.setComment(new Comment(null, null, CommentStatus.Pending, null));
            purchaseRecordRepository.save(record);
        }
    }

    @Override
    public void comment(Integer recordId, String content, Integer stars) throws Exception {
        Optional<PurchaseRecord> opRecord = purchaseRecordRepository.findById(recordId);
        if(!opRecord.isPresent()){
            throw new Exception("该记录不存在");
        }
        PurchaseRecord record = opRecord.get();
        Comment comment = record.getComment();
        if(comment == null || comment.getStatus() != CommentStatus.Pending){
            throw new Exception("无法评价");
        }
        comment.setContent(content);
        comment.setStars(stars);
        comment.setStatus(CommentStatus.Filled);
        commentRepository.save(comment);
    }

    @Override
    public Order findOrderByID(Long orderID) throws Exception{
        Optional<Order> opOrder = orderRepository.findById(orderID);
        if(!opOrder.isPresent()){
            throw new Exception("订单号错误");
        }
        Order order = opOrder.get();
        return order;
    }

    @Override
    public List<UserCommentData> getCommentByUserAndStatus(String userId, CommentStatus status) {
        List<PurchaseRecord> records = purchaseRecordRepository.findByBuyerId(userId);
        List<UserCommentData> res = new ArrayList<>();
        if(records == null) return res;
        for(PurchaseRecord record:records){
            Comment comment = record.getComment();
            if(comment == null)continue;
            if(comment.getStatus() == status){
                res.add(new UserCommentData(comment, record.getOrder(),
                        goodsRepository.findByGoodsId(record.getGoodsId()),record.getId()));
            }
        }
        return res;
    }

    @Override
    public List<GoodsCommentData> getCommentByGoods(String goodsId) {
        List<PurchaseRecord> records = purchaseRecordRepository.findByGoodsId(goodsId);
        List<GoodsCommentData> res = new ArrayList<>();
        if(records == null) return res;
        for(PurchaseRecord record:records){
            Comment comment = record.getComment();
            if(comment == null)continue;
            if(comment.getStatus() == CommentStatus.Filled){
                res.add(new GoodsCommentData(comment, record.getBuyerId()));
            }
        }
        return res;
    }
}
