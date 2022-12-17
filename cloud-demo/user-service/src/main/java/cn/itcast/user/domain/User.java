package cn.itcast.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "account")
    private String account;

    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Address> address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ShoppingCart> cart;
}
