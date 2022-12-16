package cn.itcast.order.repository;

import cn.itcast.order.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    Goods findByGoodsId(String goodsId);
    List<Goods> findByMedium(String medium);
    List<Goods> findByTag(String tag);
    List<Goods> findByMediumIn(List<String> medium);
    List<Goods> findByTagIn(List<String> tag);
    List<Goods> findByMediumInAndTagIn(List<String> medium, List<String> tag);
}
