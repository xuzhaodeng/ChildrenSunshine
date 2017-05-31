package com.pas.edu.entity.common;

import lombok.Data;

/**
 * Author : eric
 * CreateDate : 2016/9/20  11:28
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result() {
        this(ResultCode.SUCCESS);
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this(code, msg, null);
    }

    public Result(int code) {
        this(code, null);
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }
}
