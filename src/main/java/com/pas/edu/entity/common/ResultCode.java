package com.pas.edu.entity.common;

/**
 * Author : eric
 * CreateDate : 2017/5/27  14:39
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public enum ResultCode {


    /**
     * 成功
     */
    SUCCESS(1, "请求成功"),
    /**
     * 系统错误
     */
    SYS_ERROR(-1, "系统错误"),
    FAILED(0, "请求失败");


    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    private int code;
    private String msg;
}
