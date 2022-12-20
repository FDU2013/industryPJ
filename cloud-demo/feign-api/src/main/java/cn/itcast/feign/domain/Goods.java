package cn.itcast.feign.domain;

import cn.itcast.feign.common.GoodsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Goods {

    private String goodsId;
    private String name;
    private String author;
    private String publisher;
    private String medium;
    private String tag;
    private String imageUrl;
    private String details;
    private BigDecimal price;
    private GoodsStatus status;
}
