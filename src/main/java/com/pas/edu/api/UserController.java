package com.pas.edu.api;

import com.pas.edu.entity.ApplyStatusReport;
import com.pas.edu.entity.LoginRequest;
import com.pas.edu.entity.ModifyPwdRequest;
import com.pas.edu.entity.Organ;
import com.pas.edu.entity.TokenInfo;
import com.pas.edu.entity.User;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.exception.CommonException;
import com.pas.edu.service.OrganService;
import com.pas.edu.service.ReportService;
import com.pas.edu.service.UserService;
import com.pas.edu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    
    @Autowired
    ReportService reportService;

    @ApiOperation(value = "登录", notes = "手机号，密码登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult<User> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        BaseResult<User> result = new BaseResult<User>();
        User user = userService.login(loginRequest.getPhone(), loginRequest.getPassword());
        //设置返回token
        TokenInfo tokenInfo = JwtUtils.createJWT(String.valueOf(user.getId()));
       
        user.setTokenInfo(tokenInfo);
        
        ApplyStatusReport asr = reportService.getApplyStatusReport(user.getOrgId(), user.getOrgLevel());
        
        user.setApplyStatusReport(asr);
        result.setData(user);
        return result;
    }

    @ApiOperation(value = "修改密码", notes = "")
    @RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
    public Result modifyPwd(@RequestBody @Valid ModifyPwdRequest modifyPwdRequest) throws Exception {
        Result result = new Result();
        if (!modifyPwdRequest.getNewPwd().equals(modifyPwdRequest.getNewPwdConfirm())) {
            throw new CommonException("两次数据密码不一致");
        }
        userService.modifyPwd(modifyPwdRequest);
        return result;
    }
}
