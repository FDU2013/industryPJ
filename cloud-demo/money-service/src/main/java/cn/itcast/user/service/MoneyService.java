package cn.itcast.user.service;

import cn.itcast.user.Repository.MoneyRepository;
import cn.itcast.user.common.UserMoneyData;
import cn.itcast.user.domain.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoneyService {

    @Autowired
    private MoneyRepository moneyRepository;

    public boolean init(String id) {
        Money money = moneyRepository.findByID(id);
        if(money!=null){
            return false;
        }
        try{
            Money new_money = new Money(id,new BigDecimal(0));
            moneyRepository.save(new_money);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean add(UserMoneyData umd) {
        Money money = moneyRepository.findByID(umd.getID());
        money.setBalance(money.getBalance().add(umd.getMoney()));
        moneyRepository.save(money);
        return true;
    }

    public boolean decrease(UserMoneyData umd) {
        if(!isEnough(umd)){
            return false;
        }
        Money money = moneyRepository.findByID(umd.getID());
        money.setBalance(money.getBalance().subtract(umd.getMoney()));
        moneyRepository.save(money);
        return true;
    }

    public boolean isEnough(UserMoneyData umd) {
        Money money = moneyRepository.findByID(umd.getID());
        if(money==null){
            return false;
        }
        if(money.getBalance().compareTo(umd.getMoney())<0){
            return false;
        }
        return true;
    }

    public BigDecimal getMoney(String id) {
        return moneyRepository.findByID(id).getBalance();
    }
}