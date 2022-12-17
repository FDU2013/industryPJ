package cn.itcast.order.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageSearchData {
    Integer pageNum;
    Integer pageSize;
    String search;
}
