package com.pas.edu.web;

import com.pas.edu.exception.AuthorizationException;
import com.pas.edu.utils.JwtUtils;
import com.pas.edu.utils.LogUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.AccessException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : eric
 * CreateDate : 2017/5/31  16:54
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  Token校验拦截
 * Modified :
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            LogUtils.logHttpRequest(request);
            throw new AuthorizationException("非法请求,请传入Authorization");
        } else {
            Claims claims = JwtUtils.parseJWT(token, JwtUtils.token);
            if (claims == null) {
                LogUtils.logHttpRequest(request);
                throw new AuthorizationException("非法请求,token错误或失效");
            } else {
                logger.info(claims.get("userId") + "");
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        logger.info("afterCompletion");
    }
}
