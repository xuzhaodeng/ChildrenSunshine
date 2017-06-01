package com.pas.edu.api;

import com.pas.edu.entity.LoginRequest;
import com.pas.edu.entity.TokenInfo;
import com.pas.edu.entity.User;
import com.pas.edu.entity.common.Result;
import com.pas.edu.service.UserService;
import com.pas.edu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:30
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  用户接口
 * Modified :
 */
@Api(value = "用户", tags = "用户接口")
@RestController
@RequestMapping("api/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    /**
     * 登录接口
     *
     * @param loginRequest
     * @return
     */
    @ApiOperation(value = "登录", notes = "手机号，密码登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody LoginRequest loginRequest) {
        Result result = new Result();
        User user = userService.getUserByPhone(loginRequest.getPhone());
        TokenInfo tokenInfo = JwtUtils.createJWT(String.valueOf(user.getId()));
        user.setTokenInfo(tokenInfo);
        result.setData(user);
        return result;
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public Result test(@RequestBody LoginRequest loginRequest) {
        return new Result();
    }
}
