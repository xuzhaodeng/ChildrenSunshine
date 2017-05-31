package com.pas.edu.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Author : eric
 * CreateDate : 2017/5/31  17:11
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public class LogUtils {
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void logHttpRequest(HttpServletRequest request) {
        if (request != null) {
            // 记录下请求内容
            logger.info("URL : {}", request.getRequestURL().toString());
            logger.info("HTTP_METHOD : {}", request.getMethod());
            logger.info("IP : {}", request.getRemoteAddr());
            Enumeration<String> heads = request.getHeaderNames();
            while (heads.hasMoreElements()) {
                String headName = (String) heads.nextElement();
                logger.info("{}:{}", headName, request.getHeader(headName));
            }
            //获取所有参数方法一：
//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String paraName = (String) enu.nextElement();
//            System.out.println(paraName + ": " + request.getParameter(paraName));
//        }
        }
    }
}
