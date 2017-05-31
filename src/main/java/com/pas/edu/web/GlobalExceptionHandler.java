package com.pas.edu.web;

import com.pas.edu.entity.common.Result;
import com.pas.edu.entity.common.ResultCode;
import com.pas.edu.exception.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : eric
 * CreateDate : 2016/12/27  14:55
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  全局异常处理类
 * Modified :
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Result handleInvalidRequestError(AccessException ex, HttpServletResponse response) {
        return new Result(401, "非法请求");
    }

    //缺少参数异常
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Result requestMissingServletRequest(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        return new Result(400, "缺少必要参数,参数名称为" + ex.getParameterName());
    }


    @ExceptionHandler(Exception.class)
    public Result handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        Result result = new Result(ResultCode.FAILED);
        result.setMsg("系统异常 " + e.getMessage());
        return result;
    }
}
