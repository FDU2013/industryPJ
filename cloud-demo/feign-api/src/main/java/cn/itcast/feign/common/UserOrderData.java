package cn.itcast.feign.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderData {
    String buyerId;
    HashMap<String, Integer> goodsIdAndNum;
}
