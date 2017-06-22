package com.pas.edu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Author : eric
 * CreateDate : 2017/6/22  14:35
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Data
public class UserEdit {
    //用户id
    private int id;
    @ApiModelProperty(value = "用户名", example = "eric")
    @NotEmpty
    private String name;
    @NotEmpty
    @ApiModelProperty(value = "登录手机号", example = "18811111111")
    private String phone;
    @ApiModelProperty(value = "身份号", example = "420181198812256521")
    private String idCard;
    @NotEmpty
    @ApiModelProperty(value = "密码", example = "111111")
    private String password;
    @ApiModelProperty("头像链接")
    private String headImg;
    @ApiModelProperty("账号状态：1有效，2禁用")
    private int valid;
    @ApiModelProperty("简介")
    private String desc;
    @ApiModelProperty(value = "机构id", example = "2")
    private int orgId;
}
