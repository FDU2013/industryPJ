package cn.itcast.order.repository;

import cn.itcast.order.common.GoodsStatus;
import cn.itcast.order.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    Goods findByGoodsId(String goodsId);
    List<Goods> findByGoodsIdIn(List<String> goodsId);
    List<Goods> findByMedium(String medium);
    List<Goods> findByTag(String tag);
    List<Goods> findByMediumIn(List<String> medium);
    List<Goods> findByTagIn(List<String> tag);
    List<Goods> findByMediumInAndTagIn(List<String> medium, List<String> tag);
    List<Goods> findByMediumAndStatus(String medium, GoodsStatus status);
    List<Goods> findByTagAndStatus(String tag, GoodsStatus status);
    List<Goods> findByMediumInAndStatus(List<String> medium, GoodsStatus status);
    List<Goods> findByTagInAndStatus(List<String> tag, GoodsStatus status);
    List<Goods> findByMediumInAndTagInAndStatus(List<String> medium, List<String> tag, GoodsStatus status);
    List<Goods> findByStatus(GoodsStatus status);
}
