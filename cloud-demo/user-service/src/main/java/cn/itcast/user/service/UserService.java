package cn.itcast.user.service;

import cn.itcast.user.domain.Address;
import cn.itcast.user.domain.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    void addUser(User user);
    void addAddress(String userId, Address address);
    void deleteAddress(String userId, Integer addressId);
    List<Address> getUserAddress(String userId);
    void updateAddress(Address address);
    void addGoodsToCart(String userId, String goodsId, Integer num);
    void reduceGoodsOfCart(String userId, String goodsId, Integer num);
    void deleteGoodsOfCart(String userId, String goodsId);
    BigDecimal getTotalPriceOfCart(String userId);
}