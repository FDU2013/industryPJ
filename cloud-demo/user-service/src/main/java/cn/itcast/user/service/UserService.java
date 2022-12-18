package cn.itcast.user.service;

import cn.itcast.user.domain.Address;
import cn.itcast.user.domain.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    void addUser(User user) throws Exception;
    void updateUserInfo(User user) throws Exception;
    void addAddress(String userId, Address address) throws Exception;
    void deleteAddress(String userId, Integer addressId) throws Exception;
    List<Address> getUserAddress(String userId);
    void updateAddress(String userId, Address address) throws Exception;
    void addGoodsToCart(String userId, String goodsId, Integer num) throws Exception;
    void reduceGoodsOfCart(String userId, String goodsId, Integer num) throws Exception;
    void deleteGoodsOfCart(String userId, String goodsId) throws Exception;
    void emptyCartOfUser(String userId) throws Exception;
    HashMap<String, Integer> getCartOfUser(String userId) throws Exception;
    HashMap<String, Integer> purchaseInCart(String userId, List<String> goodsIdList) throws Exception;
}