package cn.itcast.feign.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginData {
    String ID;
    String password;
}
