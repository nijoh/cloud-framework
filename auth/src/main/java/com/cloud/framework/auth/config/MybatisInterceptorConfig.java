package com.cloud.framework.auth.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Mybatis配置拦截器
 */
@Configuration
public class MybatisInterceptorConfig {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 日期填充拦截器
     * */
    @PostConstruct
    public void addGenerateTimeIntercepter() {
        sqlSessionFactory.getConfiguration().addInterceptor(new GenerateTimeIntercepter());
    }
}
