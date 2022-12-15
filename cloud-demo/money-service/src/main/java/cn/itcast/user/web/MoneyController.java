package cn.itcast.user.web;


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
    public boolean add(@RequestBody UserMoneyData umd) {
        return moneyService.add(umd);
    }

    @PostMapping("/decrease")
    public boolean decrease(@RequestBody UserMoneyData umd) {
        return moneyService.decrease(umd);
    }

    @PostMapping("/isEnough")
    public boolean isEnough(@RequestBody UserMoneyData umd) {
        return moneyService.isEnough(umd);
    }

    @GetMapping("/getMoney")
    public BigDecimal getMoney(@PathVariable("id") String id) {
        return moneyService.getMoney(id);
    }
}
