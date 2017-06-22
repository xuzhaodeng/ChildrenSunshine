package com.pas.edu.web;

import com.pas.edu.entity.common.Result;
import com.pas.edu.entity.common.ResultCode;
import com.pas.edu.exception.AuthorizationException;
import com.pas.edu.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public Result handleInvalidRequestError(AuthorizationException ex, HttpServletResponse response) {
        Result result = new Result(ResultCode.UNAUTHORICATION);
        result.setMsg(ex.getMessage());
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Result result = new Result(ResultCode.REQUEST_ERROR);
        result.setMsg(ex.getMessage());
        return result;
    }

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public Result handleCommonExceptionException(CommonException ex) {
        Result result = new Result(ResultCode.REQUEST_ERROR);
        result.setMsg(ex.getMessage());
        return result;
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        Result result = new Result(ResultCode.FAILED);
        result.setMsg(e.getMessage());
        return result;
    }
}
