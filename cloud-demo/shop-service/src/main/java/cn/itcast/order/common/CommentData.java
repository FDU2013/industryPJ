package cn.itcast.order.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentData {
    Integer purchaseID;
    String content;
    Integer stars;
}
