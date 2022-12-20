package cn.itcast.order.repository;

import cn.itcast.order.domain.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Integer> {
    List<PurchaseRecord> findByBuyerId(String buyerId);
    List<PurchaseRecord> findByGoodsId(String goodsId);
}
