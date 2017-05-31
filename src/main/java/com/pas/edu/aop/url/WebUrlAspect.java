package com.pas.edu.aop.url;


import com.pas.edu.entity.common.Result;
import com.pas.edu.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @version V1.0
 * @Package com.ydj.aop.url
 * @ClassName: WebUrlAspect
 * @Description: TODO(请求拦截器)
 * @date 2017年3月10日 上午10:29:37
 */
@Aspect
@Component
public class WebUrlAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebUrlAspect.class);


    /**
     * 定义拦截规则：拦截com.pas.edu.api包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(public * com.pas.edu.api..*.*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return Result（被拦截方法的执行结果，或需要登录的错误提示。）
     * @throws Throwable
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        String message = "";
        boolean isIntercept = false;
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名
        logger.info("请求开始，方法：{}", methodName);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String url=request.getRequestURL().toString();
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            isIntercept = true;
            message = "非法请求,请传入Authorization";
        } else {
            Claims claims = JwtUtils.parseJWT(token, JwtUtils.token);
            if (claims == null) {
                isIntercept = true;
                message = "非法请求,token错误或失效";
            }
        }
        Object result = null;
        if (!isIntercept) {
            result = pjp.proceed();
        } else {
            result = new Result(-1, message);
        }
        logger.info("消耗时间 {} ms", (System.currentTimeMillis() - beginTime));
        return result;
    }


    @AfterReturning(returning = "ret", pointcut = "controllerMethodPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("响应数据 : " + ret);
    }
}
