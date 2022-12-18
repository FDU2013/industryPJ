package cn.itcast.order.repository;

import cn.itcast.order.common.OrderStatus;
import cn.itcast.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long > {
    List<Order> findByBuyerIdAndStatus(String buyerId, OrderStatus status);
}
