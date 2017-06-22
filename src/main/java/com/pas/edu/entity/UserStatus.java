package com.pas.edu.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author : eric
 * CreateDate : 2017/6/22  15:30
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Data
public class UserStatus {
    @ApiModelProperty(value = "用户id", example = "122")
    private int userId;
    @ApiModelProperty(value = "状态：1 正常 2 禁用 3 删除", example = "2")
    private int valid;
}
