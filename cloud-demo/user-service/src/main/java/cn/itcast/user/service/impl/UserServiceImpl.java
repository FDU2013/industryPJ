package cn.itcast.user.service.impl;

import cn.itcast.user.domain.Address;
import cn.itcast.user.domain.ShoppingCart;
import cn.itcast.user.domain.User;
import cn.itcast.user.repository.AddressRepository;
import cn.itcast.user.repository.ShoppingCartRepository;
import cn.itcast.user.repository.UserRepository;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public void addUser(User user) throws Exception {
        User exist = userRepository.findByAccount(user.getAccount());
        if(exist != null){
            throw new Exception("用户id已存在");
        }
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public void updateUserInfo(User user) throws Exception {
        User oldUser = userRepository.findByAccount(user.getAccount());
        if(oldUser == null){
            throw new Exception("用户不存在");
        }
        String name = user.getName();
        if(name != null && !name.equals("")){
            oldUser.setName(name);
        }
        String email = user.getEmail();
        if(email != null && !email.equals("")){
            oldUser.setEmail(email);
        }
        String phone = user.getPhone();
        if(phone != null && !phone.equals("")){
            oldUser.setPhone(phone);
        }
        userRepository.save(oldUser);
    }

    @Override
    public void addAddress(String account, Address address) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        address.setUser(user);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(String account, Integer addressId) throws Exception {
        Optional<Address> opAddress = addressRepository.findById(addressId);
        if(!opAddress.isPresent()){
            throw new Exception("地址不存在");
        }
        Address address = opAddress.get();
        if(!address.getUser().getAccount().equals(account)){
            throw new Exception("用户与地址不匹配");
        }
        addressRepository.delete(address);
    }

    @Override
    public List<Address> getUserAddress(String account) {
        return userRepository.findByAccount(account).getAddress();
    }

    @Override
    public void updateAddress(String account, Address address) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        Optional<Address> opAddress = addressRepository.findById(address.getId());
        if(!opAddress.isPresent()){
            throw new Exception("地址不存在");
        }
        Address oldAddress = opAddress.get();
        String name = address.getName();
        if(name != null && !name.equals("")){
            oldAddress.setName(name);
        }
        String phone = address.getPhone();
        if(phone != null && !phone.equals("")){
            oldAddress.setPhone(phone);
        }
        String details = address.getDetails();
        if(details != null && !details.equals("")){
            oldAddress.setDetails(details);
        }
        String description = address.getDescription();
        if(description != null && !description.equals("")){
            oldAddress.setDescription(description);
        }
        addressRepository.save(oldAddress);
    }

    @Override
    public void addGoodsToCart(String account, String goodsId, Integer num) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        ShoppingCart cart = shoppingCartRepository.findByUserAndGoodsId(user, goodsId);
        if(cart == null){
            cart = new ShoppingCart(null, user, goodsId, num);
        } else {
            cart.setGoodsNum(cart.getGoodsNum() + num);
        }
        shoppingCartRepository.save(cart);
    }

    @Override
    public void reduceGoodsOfCart(String account, String goodsId, Integer num) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        ShoppingCart cart = shoppingCartRepository.findByUserAndGoodsId(user, goodsId);
        if(cart == null){
            throw new Exception("该商品不在购物车中");
        }
        int result = cart.getGoodsNum() - num;

        if(result > 0){
            cart.setGoodsNum(result);
            shoppingCartRepository.save(cart);
        } else {
            shoppingCartRepository.delete(cart);
        }

    }

    @Override
    public void deleteGoodsOfCart(String account, String goodsId) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        shoppingCartRepository.deleteByUserAndGoodsId(user, goodsId);
    }

    @Override
    public void emptyCartOfUser(String account) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        shoppingCartRepository.deleteByUser(user);
    }

    @Override
    public HashMap<String, Integer> getCartOfUser(String account) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        List<ShoppingCart> carts = shoppingCartRepository.findByUser(user);
        HashMap<String, Integer> res = new HashMap<>();
        for(ShoppingCart cart:carts){
            res.put(cart.getGoodsId(), cart.getGoodsNum());
        }
        return res;
    }

    @Override
    public HashMap<String, Integer> purchaseInCart(String account, List<String> goodsIdList) throws Exception {
        User user = userRepository.findByAccount(account);
        if(user == null){
            throw new Exception("用户不存在");
        }
        List<ShoppingCart> carts = shoppingCartRepository.findByUserAndGoodsIdIn(user, goodsIdList);
        HashMap<String, Integer> res = new HashMap<>();
        for(ShoppingCart cart:carts){
            res.put(cart.getGoodsId(), cart.getGoodsNum());
            shoppingCartRepository.delete(cart);
        }
        return res;
    }

}
