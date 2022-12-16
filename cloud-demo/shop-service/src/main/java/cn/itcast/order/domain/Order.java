package cn.itcast.order.domain;

import cn.itcast.order.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "total_price")
    private String totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<PurchaseRecord> records;
}
