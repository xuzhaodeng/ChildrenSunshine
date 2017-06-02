package com.pas.edu.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Author : eric
 * CreateDate : 2017/6/2  10:47
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Data
public class ModifyPwdRequest {
    private int userId;
    @NotEmpty(message = "原密码不能为空")
    private String oldPwd;
    @NotEmpty(message = "新密码不能为空")
    private String newPwd;
    @NotEmpty(message = "确认密码不能为空")
    private String newPwdConfirm;
}
