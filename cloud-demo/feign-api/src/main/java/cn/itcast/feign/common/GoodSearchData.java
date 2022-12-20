package cn.itcast.feign.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodSearchData {
    List<String> medium;
    List<String> tag;
    Integer pageNum;
    Integer pageSize;
    String search;
}
