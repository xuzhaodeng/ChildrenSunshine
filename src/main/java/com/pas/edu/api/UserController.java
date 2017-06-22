package com.pas.edu.api;

import com.pas.edu.entity.*;
import com.pas.edu.entity.common.BaseResult;
import com.pas.edu.entity.common.Result;
import com.pas.edu.exception.CommonException;
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
import java.util.List;

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

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
    public Result modifyPwd(@RequestBody @Valid ModifyPwdRequest modifyPwdRequest) throws Exception {
        Result result = new Result();
        if (!modifyPwdRequest.getNewPwd().equals(modifyPwdRequest.getNewPwdConfirm())) {
            throw new CommonException("两次数据密码不一致");
        }
        userService.modifyPwd(modifyPwdRequest);
        return result;
    }


    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestBody @Valid UserEdit userEdit) throws Exception {
        Result result = new Result();
        int id = userService.add(userEdit);
        result.setData(id);
        return result;
    }

    @ApiOperation(value = "编辑", notes = "编辑用户")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public Result edit(@RequestBody @Valid UserEdit userEdit) throws Exception {
        Result result = new Result();
        userService.edit(userEdit);
        return result;
    }

    @ApiOperation(value = "用户账号状态修改", notes = "用户账号状态修改：删除/禁用/启动")
    @RequestMapping(value = "valid", method = RequestMethod.POST)
    public Result valid(@RequestBody List<UserStatus> userStatusList) throws Exception {
        Result result = new Result();
        userService.modifyUserStatus(userStatusList);
        return result;
    }
}
