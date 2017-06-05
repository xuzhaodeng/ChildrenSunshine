package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:32
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  登录请求实体
 * Modified :
 */
@Data
public class LoginRequest {
    @ApiModelProperty(value = "登录手机号")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
}
