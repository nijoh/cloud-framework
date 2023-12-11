package com.cloud.framework.auth.config.security;

import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.service.AccountUserMangeService;
import com.cloud.framework.auth.service.AccountUserQueryService;
import com.cloud.framework.integrate.cache.RedisCacheTemplate;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 登录认证逻辑
 */
@Component
public class CloudAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AccountUserMangeService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisCacheTemplate redisCacheqemplate;

    @Autowired
    private AccountUserQueryService accountUserQueryService;

    /**
     * 验证登陆认证逻辑
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //用户名
        String name = authentication.getPrincipal().toString();
        //已加密密码
        String password = authentication.getCredentials().toString();
        //查找用户
        AccountUser user = accountUserQueryService.findAccountUser(name);
        AssertUtil.notNull(user, CloudConstant.NOT_ACCOUNTUSER);
        //比对密码
        boolean loginResult = passwordEncoder.matches(password, user.getPassword());
        if (loginResult) {
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
        throw new AuthenticationException("认证失败") {
        };
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
