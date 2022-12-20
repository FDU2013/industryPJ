package cn.itcast.feign.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToSee {
    String userID;
    String name;
    String email;
    String phone;
}
