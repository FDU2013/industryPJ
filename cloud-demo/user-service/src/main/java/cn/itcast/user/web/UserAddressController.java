package cn.itcast.user.web;

import cn.itcast.feign.common.Result;
import cn.itcast.user.common.AddAddressData;
import cn.itcast.user.common.AddressEntry;
import cn.itcast.user.domain.Address;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAddressController {
    @Autowired
    private UserService userService;

    @PostMapping("/addAddress")
    public Result addAddress(HttpServletRequest request, @RequestBody AddAddressData addAddressData) {
        String ID = request.getHeader("ID");
        try{
            userService.addAddress(ID,addAddressData.ToAddress());
            return  Result.succ(addAddressData.getDetails(),"地址添加成功");
        }catch (Exception e){
            return Result.fail(300,e.getMessage());
        }
    }

    @PostMapping("/deleteAddress")
    public Result deleteAddress(@RequestBody Integer addressID, HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            userService.deleteAddress(ID,addressID);
            return  Result.succ(addressID,"地址删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(310,e.getMessage());
        }
    }

    @PostMapping("/allAddress")
    public Result allAddress(HttpServletRequest request) {
        String ID = request.getHeader("ID");
        try{
            List<Address>  addresses = userService.getUserAddress(ID);
            List<AddressEntry> addressEntries = AddressEntry.GetEntry(addresses);
            return  Result.succ(addressEntries);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(311,e.getMessage());
        }
    }
}
