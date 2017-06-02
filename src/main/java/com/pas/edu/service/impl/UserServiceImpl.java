package com.pas.edu.service.impl;

import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.ModifyPwdRequest;
import com.pas.edu.entity.User;
import com.pas.edu.exception.CommonException;
import com.pas.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:57
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public void modifyPwd(ModifyPwdRequest modifyPwdRequest) throws Exception {
        User user = userDao.getUserById(modifyPwdRequest.getUserId());
        if (user == null)
            throw new CommonException("用户不存在");
        if (!user.getPassword().equals(modifyPwdRequest.getOldPwd()))
            throw new CommonException("密码错误");
        userDao.updatePwd(modifyPwdRequest.getUserId(), modifyPwdRequest.getNewPwd());
    }


}
