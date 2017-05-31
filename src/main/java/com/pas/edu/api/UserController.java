package com.pas.edu.api;

import com.pas.edu.entity.LoginRequest;
import com.pas.edu.entity.User;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:30
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  用户接口
 * Modified :
 */
@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping("login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByPhone(loginRequest.getPhone());
        Result result = new Result();
        result.setData(user);
        return result;
    }

    @RequestMapping("test")
    public Result test(@RequestBody LoginRequest loginRequest) {
        return new Result();
    }
}
