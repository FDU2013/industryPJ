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
    @Column(name = "record_id")
    private Integer recordId;
    @Column(name = "buyer_id")
    private String buyerId;
    @Column(name = "goods_id")
    private String goodsId;

    @ManyToOne
    @JoinColumn(name = "order")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment")
    private Comment comment;
}
