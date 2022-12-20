package cn.itcast.feign.domain;


import cn.itcast.feign.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
}
