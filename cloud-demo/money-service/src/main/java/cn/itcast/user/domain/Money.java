package cn.itcast.user.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "money")
public class Money {
    @Id
    @Column(name = "ID", unique = true)
    String ID;

    @Column(name = "balance", precision = 19, scale = 2)
    BigDecimal balance;

}
