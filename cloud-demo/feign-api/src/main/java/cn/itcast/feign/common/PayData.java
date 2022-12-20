package cn.itcast.feign.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayData {
    String userID;
    Long orderID;
    String notes;
    String address;
}
