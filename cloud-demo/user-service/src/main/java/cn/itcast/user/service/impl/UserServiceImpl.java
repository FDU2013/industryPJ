package cn.itcast.user.service.impl;

import cn.itcast.user.domain.Address;
import cn.itcast.user.domain.User;
import cn.itcast.user.repository.AddressRepository;
import cn.itcast.user.repository.ShoppingCartRepository;
import cn.itcast.user.repository.UserRepository;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    AddressRepository addressRepository;
    ShoppingCartRepository shoppingCartRepository;
    UserRepository userRepository;
    @Autowired
    UserServiceImpl(AddressRepository addressRepository, ShoppingCartRepository shoppingCartRepository, UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void addUser(User user) {

    }

    @Override
    public void addAddress(String userId, Address address) {

    }

    @Override
    public void deleteAddress(String userId, Integer addressId) {

    }

    @Override
    public List<Address> getUserAddress(String userId) {
        return null;
    }

    @Override
    public void updateAddress(Address address) {

    }

    @Override
    public void addGoodsToCart(String userId, String goodsId, Integer num) {

    }

    @Override
    public void reduceGoodsOfCart(String userId, String goodsId, Integer num) {

    }

    @Override
    public void deleteGoodsOfCart(String userId, String goodsId) {

    }

    @Override
    public BigDecimal getTotalPriceOfCart(String userId) {
        return null;
    }
}
