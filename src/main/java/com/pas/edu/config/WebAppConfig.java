package com.pas.edu.config;

import com.pas.edu.web.TokenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author : eric
 * CreateDate : 2017/5/19  15:14
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :  intercept config
 * Modified :
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment env;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //是否拦截
        boolean isTokenInterceptEnable = Boolean.parseBoolean(env.getProperty("interceptor.token.enable").trim());
        if (isTokenInterceptEnable) {
            //配置token拦截path
            String excludePaths = env.getProperty("interceptor.token.excludePath");
            String pathPatterns = env.getProperty("interceptor.token.pathPatterns");
            String[] excludePathArray = excludePaths.split(";");
            String[] pathPatternArray = pathPatterns.split(";");
            registry.addInterceptor(new TokenInterceptor())
                    .addPathPatterns(pathPatternArray)
                    .excludePathPatterns(excludePathArray);
        }
    }
}