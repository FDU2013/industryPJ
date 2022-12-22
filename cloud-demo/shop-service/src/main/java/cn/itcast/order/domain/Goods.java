package cn.itcast.order.domain;

import cn.itcast.order.common.GoodsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @Column(name = "goods_id", nullable = false)
    private String goodsId;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "medium")
    private String medium;
    @Column(name = "tag")
    private String tag;
    @Lob
    @Column(name = "image_url", columnDefinition="TEXT")
    private String imageUrl;
    @Column(name = "details")
    private String details;
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "status")
    private GoodsStatus status;
}
