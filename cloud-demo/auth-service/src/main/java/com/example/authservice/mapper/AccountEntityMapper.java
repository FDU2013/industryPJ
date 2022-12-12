package com.example.authservice.mapper;

import com.example.authservice.pojo.AccountEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountEntityMapper {
    @Select("select * from account where username = {#username}")
    AccountEntity findByName(@Param("username") String username);
}
