package cn.itcast.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserMoneyData {
    String ID;
    BigDecimal money;
}
