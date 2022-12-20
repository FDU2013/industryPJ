package cn.itcast.order.common;


import cn.itcast.feign.common.OrderStatus;
import cn.itcast.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderToSee {
    private Long id;
    private String buyerId;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String address;
    private String notes;
    private String waybillNum;

    public static List<OrderToSee> getToSee(List<Order> orders){
        List<OrderToSee> ret = new ArrayList<>();
        for (Order order:orders){
            ret.add(new OrderToSee(
                    order.getId(),
                    order.getBuyerId(),
                    order.getTotalPrice(),
                    order.getStatus(),
                    order.getAddress(),
                    order.getNotes(),
                    order.getWaybillNum()
            ));
        }
        return  ret;
    }
}
