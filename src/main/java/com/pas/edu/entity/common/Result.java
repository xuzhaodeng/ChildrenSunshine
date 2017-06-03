package com.pas.edu.entity.common;

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
public class Result extends BaseResult<Object> {

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        super(code, msg, data);
    }

    public Result(int code, String msg) {
        super(code, msg);
    }

    public Result(int code) {
        super(code);
    }

    public Result(ResultCode resultCode) {
        super(resultCode);
    }

    public void setResultCode(ResultCode resultCode) {
        setCode(resultCode.code());
        setMsg(resultCode.msg());
    }
}
