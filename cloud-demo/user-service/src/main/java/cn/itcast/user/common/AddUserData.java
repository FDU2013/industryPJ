package cn.itcast.user.common;

import cn.itcast.user.domain.User;
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

    public User ToUser(){
        return new User(
                this.account,
                this.name,
                this.email,
                this.phone,
                null,
                null
        );
    }
}
