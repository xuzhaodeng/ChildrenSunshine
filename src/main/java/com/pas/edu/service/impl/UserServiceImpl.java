package com.pas.edu.service.impl;

import com.pas.edu.dao.OrganDao;
import com.pas.edu.dao.UserDao;
import com.pas.edu.entity.ModifyPwdRequest;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.User;
import com.pas.edu.entity.UserEdit;
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

    @Autowired
    OrganDao organDao;

    @Override
    public User login(String phone, String password) throws Exception {
        User user = userDao.getUserByPhone(phone);
        if (user == null)
            throw new CommonException("用户不存在");
        if (!user.getPassword().equals(password))
            throw new CommonException("密码错误");
        if (user.getValid() != 1)
            throw new CommonException("该用户已被禁用");
        return user;

    }

    @Override
    public User getUser(int id) {
        return userDao.getUserById(id);
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

    @Override
    public int add(UserEdit userEdit) throws Exception {
        User user = userDao.getUserByPhone(userEdit.getPhone());
        if (user != null) {
            throw new CommonException("登录手机号已存在!");
        }
        if (organDao.getOrgan(userEdit.getOrgId()) == null) {
            throw new CommonException("所属机构不存在!");
        }
        userEdit.setValid(1);
        userDao.add(userEdit);
        return userEdit.getId();
    }

    @Override
    public void edit(UserEdit userEdit) throws Exception {
        User user = userDao.getUserById(userEdit.getId());
        if (user == null) {
            throw new CommonException("用户不存在!");
        }
        //判断修改后的登录手机号是否已经存在
        User exitUser = userDao.getUserByPhone(userEdit.getPhone());
        if (exitUser != null && !exitUser.getPhone().equals(user.getPhone())) {
            throw new CommonException("登录手机号已存在!");
        }
        if (organDao.getOrgan(userEdit.getOrgId()) == null) {
            throw new CommonException("所属机构不存在!");
        }
        userDao.update(userEdit);
    }
}
