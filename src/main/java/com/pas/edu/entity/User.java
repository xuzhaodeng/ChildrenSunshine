package com.pas.edu.entity;

import com.alibaba.druid.sql.parser.Token;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author : eric
 * CreateDate : 2017/5/31  10:24
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Data
public class User {
    //用户id
    private int id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("登录手机号")
    private String phone;
    @ApiModelProperty("身份号")
    private String idCard;
    //密码
    @JSONField(serialize = false)
    private String password;
    @ApiModelProperty("头像链接")
    private String headImg;
    //是否有效1有效，2禁用
    private int valid;
    //简介
    private String desc;
    //所属机构
    private int orgId;
    private String orgName;
    private String orgCode;
    private String orgParentId;
    private int orgLevel;
    private TokenInfo tokenInfo;
}
