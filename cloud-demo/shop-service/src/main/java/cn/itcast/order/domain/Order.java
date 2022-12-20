package cn.itcast.order.domain;

import cn.itcast.order.common.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<PurchaseRecord> records;

    @Column(name = "address")
    private String address;

    @Column(name = "waybill_num")
    private String waybillNum;
}
