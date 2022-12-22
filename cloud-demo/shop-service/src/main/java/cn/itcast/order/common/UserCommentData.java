package cn.itcast.order.common;

import cn.itcast.order.domain.Comment;
import cn.itcast.order.domain.Goods;
import cn.itcast.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentData {
    Comment comment;
    Order order;
    Goods goods;
    Integer purchaseID;

    public UserCommentDataForShow forShow(){
        return new UserCommentDataForShow(
                this.order.getId(),
                this.purchaseID,
                this.goods.getGoodsId(),
                this.goods.getName(),
                this.goods.getImageUrl(),
                this.comment.getContent(),
                this.comment.getStars()
        );
    }
}
