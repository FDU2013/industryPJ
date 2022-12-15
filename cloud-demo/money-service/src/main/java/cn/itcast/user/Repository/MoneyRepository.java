package cn.itcast.user.Repository;

import cn.itcast.user.domain.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money,Long> {
    Money findByID(String ID);
}
