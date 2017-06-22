package com.pas.edu.dao;

import com.pas.edu.entity.User;
import com.pas.edu.entity.UserEdit;
import com.pas.edu.entity.UserStatus;
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

    User getUserById(@Param("userId") int userId);

    User getUserByPhoneOrIdCard(@Param("phone") String phone,
                                @Param("idCard") String idCard);
    void updatePwd(@Param("userId") int userId, @Param("password") String password);

    int add(UserEdit userEdit);

    //查询所有的用户，不包括已删除用户
    List<User> getUserList(@Param("notDel") boolean notDel);

    void update(UserEdit userEdit);

    void updateStatus(UserStatus userStatus);
}
