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
    @ApiModelProperty("账号状态：1有效，2禁用")
    private int valid;
    @ApiModelProperty("简介")
    private String desc;
    @ApiModelProperty("机构id")
    private int orgId;
    @ApiModelProperty("机构名称")
    private String orgName;
    @ApiModelProperty("机构代号")
    private String orgCode;
    @ApiModelProperty("上级机构id")
    private String orgParentId;
    @ApiModelProperty("机构级别：1.市 2.区县 3.镇 4.村")
    private int orgLevel;
    private TokenInfo tokenInfo;
}
