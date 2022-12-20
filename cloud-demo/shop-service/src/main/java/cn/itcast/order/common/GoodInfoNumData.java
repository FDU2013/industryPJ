package cn.itcast.order.common;


import cn.itcast.order.domain.Goods;
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

    public static GoodInfoNumData getPair(Goods good,Integer num){
        return new GoodInfoNumData(
                good.getGoodsId(),
                good.getName(),
                good.getAuthor(),
                good.getPublisher(),
                good.getMedium(),
                good.getTag(),
                good.getImageUrl(),
                good.getDetails(),
                good.getPrice(),
                good.getStatus(),
                num
        );
    }
}
