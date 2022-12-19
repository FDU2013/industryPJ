package cn.itcast.user.web;


import cn.itcast.feign.clients.AuthClient;
import cn.itcast.feign.clients.MoneyClient;
import cn.itcast.feign.common.Result;
import cn.itcast.user.common.AddUserData;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthClient authClient;


    @PostMapping("/addUser")
    public boolean addUser(@RequestBody AddUserData user){
        try{
            userService.addUser(user.ToUser());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @PostMapping("/info")
    public Result info(HttpServletRequest request) {
        String ID = request.getHeader("ID");
        Map<String,Object> map = new HashMap<>();
        try{
            User user = userService.getUserByAccount(ID);
            map.put("userID",ID);
            map.put("name",user.getName());
            map.put("email",user.getEmail());
            map.put("phone",user.getPhone());
            return  Result.succ(map,"获取个人信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(310,e.getMessage());
        }
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(HttpServletRequest request,@RequestBody Map<String,Object> map) {
        String ID = request.getHeader("ID");
        try{
            User user = userService.getUserByAccount(ID);
            user.setAccount(ID);
            user.setName(map.get("name").toString());
            user.setEmail(map.get("email").toString());
            user.setPhone(map.get("phone").toString());
            userService.updateUserInfo(user);
            return  Result.succ(ID,"修改个人信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(310,e.getMessage());
        }

    }

    @PostMapping("/changePassword")
    public Result changePassword(HttpServletRequest request,@RequestBody Map<String,String> map) {
        String ID = request.getHeader("ID");
        try{
            map.put("ID",request.getHeader("ID"));
            if(!authClient.changePassword(map))throw new Exception("修改密码失败");
            return  Result.succ(ID,"修改密码成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(310,e.getMessage());
        }
    }

}
