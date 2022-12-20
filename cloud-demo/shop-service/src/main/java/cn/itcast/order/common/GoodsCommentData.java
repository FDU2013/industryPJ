package cn.itcast.order.common;

import cn.itcast.order.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCommentData {
    Comment comment;
    String userAccount;
}
