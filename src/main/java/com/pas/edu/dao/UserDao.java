package com.pas.edu.dao;

import com.pas.edu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:24
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public interface UserDao {
    User getUserByPhone(@Param("phone") String phone);

    List<User> getUserAll();

    User getUserById(@Param("userId") int userId);

    void updatePwd(@Param("userId") int userId, @Param("password") String password);

}
