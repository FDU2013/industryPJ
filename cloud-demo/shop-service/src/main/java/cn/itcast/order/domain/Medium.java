package cn.itcast.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mediums")
public class Medium {
    @Id
    @Column(name = "type")
    private String type;
}
