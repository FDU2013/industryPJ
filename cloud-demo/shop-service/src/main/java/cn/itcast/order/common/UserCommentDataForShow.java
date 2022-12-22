package cn.itcast.order.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentDataForShow {
    Long orderID;
    Integer purchaseID;
    String goodID;
    String goodName;
    String url;
    String content;
    Integer stars;
}
