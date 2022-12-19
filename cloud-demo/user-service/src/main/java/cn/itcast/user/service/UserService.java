package cn.itcast.user.service;

import cn.itcast.user.domain.Address;
import cn.itcast.user.domain.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    void addUser(User user) throws Exception;
    User getUserByAccount(String account) throws Exception;
    List<User> getAllUser() throws Exception;
    void updateUserInfo(User user) throws Exception;
    void addAddress(String account, Address address) throws Exception;
    void deleteAddress(String account, Integer addressId) throws Exception;
    List<Address> getUserAddress(String account);
    void updateAddress(String account, Address address) throws Exception;
    void addGoodsToCart(String account, String goodsId, Integer num) throws Exception;
    void reduceGoodsOfCart(String account, String goodsId, Integer num) throws Exception;
    void deleteGoodsOfCart(String account, String goodsId) throws Exception;
    void emptyCartOfUser(String account) throws Exception;
    HashMap<String, Integer> getCartOfUser(String account) throws Exception;
    HashMap<String, Integer> purchaseInCart(String account, List<String> goodsIdList) throws Exception;
}