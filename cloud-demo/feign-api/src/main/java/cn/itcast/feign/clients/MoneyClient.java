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

    @PostMapping("/init")
    public boolean init(@RequestBody String id);

    @PostMapping("/add")
    public boolean add(@RequestBody UserMoneyData umd);

    @PostMapping("/decrease")
    public boolean decrease(@RequestBody UserMoneyData umd);

    @PostMapping("/isEnough")
    public boolean isEnough(@RequestBody UserMoneyData umd);

    @GetMapping("/getMoney")
    public BigDecimal getMoney(@PathVariable("id") String id);
}
