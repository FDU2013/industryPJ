package cn.itcast.feign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserData {
    private String account;
    private String name;
    private String email;
    private String phone;
}