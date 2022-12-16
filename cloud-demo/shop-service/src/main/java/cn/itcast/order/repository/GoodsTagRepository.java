package cn.itcast.order.repository;

import cn.itcast.order.domain.GoodsTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsTagRepository extends JpaRepository<GoodsTag, String> {
    GoodsTag findByName(String name);
}
