package com.pas.edu.entity;

import lombok.Data;

/**
 * Author : eric
 * CreateDate : 2017/6/1  11:22
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  token信息
 * Modified :
 */
@Data
public class TokenInfo {
    private String token;
    private long expireTime;
}
