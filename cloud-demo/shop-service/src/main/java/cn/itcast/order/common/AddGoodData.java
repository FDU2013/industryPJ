package cn.itcast.order.common;

import cn.itcast.order.domain.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGoodData {
    private String goodsId;
    private String name;
    private String author;
    private String publisher;
    private String medium;
    private String tag;
    private String imageUrl;
    private String details;
    private String price;

    public Goods ToGoods(){
        return new Goods(
                this.goodsId,
                this.name,
                this.author,
                this.publisher,
                this.medium,
                this.tag,
                this.imageUrl,
                this.details,
                new BigDecimal(this.price),
                GoodsStatus.ForSale
        );
    }
}
