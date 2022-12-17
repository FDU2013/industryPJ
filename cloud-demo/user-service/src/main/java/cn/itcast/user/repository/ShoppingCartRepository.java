package cn.itcast.user.repository;

import cn.itcast.user.domain.ShoppingCart;
import cn.itcast.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findByUserAndGoodsId(User user, String goodsId);
    List<ShoppingCart> findByUser(User user);
    List<ShoppingCart> deleteByUser(User user);
    List<ShoppingCart> deleteByUserAndGoodsId(User user, String goodsId);
}
