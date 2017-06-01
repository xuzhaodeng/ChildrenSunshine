package com.pas.edu.entity;

import com.alibaba.druid.sql.parser.Token;
import com.alibaba.fastjson.annotation.JSONField;
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
    private int id;
    private String name;
    private String phone;
    private String idCard;
    @JSONField(serialize = false)
    private String password;
    private String headImg;
    private int valid;
    private String desc;
    private int orgId;
    private String orgName;
    private String orgCode;
    private String orgParentId;
    private int orgLevel;
    private TokenInfo tokenInfo;
}
