package cn.itcast.user.repository;

import cn.itcast.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByAccount(String account);
}
