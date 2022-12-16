package cn.itcast.order.repository;

import cn.itcast.order.domain.Medium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<Medium, Long> {
    Medium findByName(String name);
}
