package cn.itcast.user.common;

import cn.itcast.user.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntry {
    private Integer addressID;
    private String name;
    private String phone;
    private String details;
    private String description;

    public static List<AddressEntry> GetEntry(List<Address> addresses){
        List<AddressEntry> ret = new ArrayList<>();
        for(Address address:addresses){
            ret.add(new AddressEntry(
                    address.getId(),
                    address.getName(),
                    address.getPhone(),
                    address.getDetails(),
                    address.getDescription()
            ));
        }
        return ret;
    }
}
