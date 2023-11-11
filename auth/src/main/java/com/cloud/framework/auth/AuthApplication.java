package com.cloud.framework.auth;

import com.cloud.framework.integrate.cache.RedisCacheConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cloud.framework.auth.config.MyBaseMapper")
@Import({RedisCacheConfig.class})
@EnableDiscoveryClient
public class AuthApplication {

    public static void main(String[] args) {
         SpringApplication.run(AuthApplication.class, args);
    }

}
