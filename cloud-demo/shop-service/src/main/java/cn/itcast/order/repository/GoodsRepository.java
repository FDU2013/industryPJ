package cn.itcast.order.repository;

import cn.itcast.order.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Goods findById(String id);
}
