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
    SUCCESS(200, "请求成功"),
    /**
     * 请求出错
     */
    REQUEST_ERROR(400, "请求出错"),

    /**
     * 非法请求
     */
    UNAUTHORICATION(401, "未授权"),
    /**
     * 禁止
     */
    FORBID(403, "禁止请求"),

    /**
     * 系统错误
     */
    SYS_ERROR(500, "系统错误"),
    /**
     * 失败
     */
    FAILED(-1, "请求失败");


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
