package cn.itcast.user.common;

import cn.itcast.user.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressData {

    private String name;
    private String phone;
    private String details;
    private String description;

    public Address ToAddress(){
        return new Address(
                null,
                null,
                this.name,
                this.phone,
                this.details,
                this.description
        );
    }
}
