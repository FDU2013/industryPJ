package cn.itcast.feign.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodInfoNumData {

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
    private Integer num;
}
