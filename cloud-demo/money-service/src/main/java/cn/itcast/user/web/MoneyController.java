package cn.itcast.user.web;


import cn.itcast.feign.common.Result;
import cn.itcast.user.common.UserMoneyData;
import cn.itcast.user.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/money")

public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    @PostMapping("/init")
    public boolean init(@RequestBody String id) {
        return moneyService.init(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody UserMoneyData umd) {
        if(moneyService.add(umd)){
            return Result.succ(umd.getID(),"充值成功");
        }
        return Result.fail(330,"充值失败，账号不存在");
    }

    @PostMapping("/decrease")
    public boolean decrease(@RequestBody UserMoneyData umd) {
        return moneyService.decrease(umd);
    }

    @PostMapping("/isEnough")
    public boolean isEnough(@RequestBody UserMoneyData umd) {
        return moneyService.isEnough(umd);
    }

    @PostMapping("/getMoney")
    public BigDecimal getMoney(@RequestBody String id) {
        return moneyService.getMoney(id);
    }
}
