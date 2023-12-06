package com.cloud.framework.auth.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConifg implements WebMvcConfigurer {
    @Autowired
    private AccountUserHandlerInterceptor accountUserHandlerInterceptor;

    @Autowired
    private AuthMsHandlerInterceptor authMsHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截顺序按照加入顺序,order执行优先级
        registry.addInterceptor(accountUserHandlerInterceptor)
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/auth/**");//排除登录、注册
        registry.addInterceptor(authMsHandlerInterceptor)
                .addPathPatterns("/**");//拦截所有
    }
}
