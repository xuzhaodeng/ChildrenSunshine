package com.pas.edu.entity.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author : eric
 * CreateDate : 2016/9/20  11:28
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc : api结果返回
 * Modified :
 */
@Data
public class BaseResult<T> {
    @ApiModelProperty(value = "响应码")
    private int code;
    @ApiModelProperty(value = "响应说明")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    public BaseResult() {
        this(ResultCode.SUCCESS);
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(int code, String msg) {
        this(code, msg, null);
    }

    public BaseResult(int code) {
        this(code, null);
    }

    public BaseResult(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }
}
