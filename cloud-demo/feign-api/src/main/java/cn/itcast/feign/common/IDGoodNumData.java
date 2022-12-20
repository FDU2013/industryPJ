package cn.itcast.feign.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IDGoodNumData {
    String ID;
    String goodID;
    Integer num;
}
