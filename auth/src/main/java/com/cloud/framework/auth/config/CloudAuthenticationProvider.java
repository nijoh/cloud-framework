package com.cloud.framework.auth.config;

import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.model.common.CloudConstant;
import com.cloud.framework.utils.AsserUtil;
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
    private AccountUserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

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
        AccountUser user = userService.findAccountUserByEmail(name);
        AsserUtil.notNull(user, CloudConstant.AUTH_HMODEL,CloudConstant.NOT_ACCOUNTUSER);
        //比对密码
        boolean loginResult = passwordEncoder.matches(password, user.getPassword());
        if (loginResult) {
            return new UsernamePasswordAuthenticationToken(user.getEmail(), null, Collections.emptyList());
        }
        throw new AuthenticationException("认证失败") {
        };
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
