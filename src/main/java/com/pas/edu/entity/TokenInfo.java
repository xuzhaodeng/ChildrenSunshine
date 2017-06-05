package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("有效时间")
    private long expireTime;
}
