package cn.itcast.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase_records")
public class PurchaseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "buyer_id")
    private String buyerId;
    @Column(name = "goods_id")
    private String goodsId;
    @Column(name = "goods_num")
    private String goodsNum;
    @Column(name = "total+price")
    private String totalPrice;
    @ManyToOne
    @JoinColumn(name = "order")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment")
    private Comment comment;
}
