package cn.itcast.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase_records")
public class PurchaseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "buyer_id")
    private String buyerId;
    @Column(name = "goods_id")
    private String goodsId;
    @Column(name = "goods_num")
    private Integer goodsNum;
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment")
    private Comment comment;
}
