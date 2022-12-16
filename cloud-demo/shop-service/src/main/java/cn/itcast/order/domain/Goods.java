package cn.itcast.order.domain;

import cn.itcast.order.common.MediumType;
import cn.itcast.order.common.TagType;
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
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "medium")
    private String medium;
    @Column(name = "tag")
    private String tag;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "details")
    private String details;
    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;
}
