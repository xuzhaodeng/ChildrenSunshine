package com.pas.edu.service;

import com.pas.edu.entity.ModifyPwdRequest;
import com.pas.edu.entity.User;
import com.pas.edu.entity.UserEdit;
import com.pas.edu.entity.UserStatus;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:56
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public interface UserService {
    User login(String phone,String password) throws Exception;

    User getUser(int id);

    void modifyPwd(ModifyPwdRequest modifyPwdRequest) throws Exception;


    int add(UserEdit userEdit) throws Exception;

    void edit(UserEdit userEdit)throws Exception;

    void modifyUserStatus(List<UserStatus> userStatusList)throws Exception;
}
