package cn.itcast.feign.clients;

import cn.itcast.feign.common.UserMoneyData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient("moneyservice")
public interface MoneyClient {

    @PostMapping("/money/init")
    public boolean init(@RequestBody String id);

    @PostMapping("/money/add")
    public boolean add(@RequestBody UserMoneyData umd);

    @PostMapping("/money/decrease")
    public boolean decrease(@RequestBody UserMoneyData umd);

    @PostMapping("/money/isEnough")
    public boolean isEnough(@RequestBody UserMoneyData umd);

    @PostMapping("/money/getMoney")
    public BigDecimal getMoney(@RequestBody String id);
}
