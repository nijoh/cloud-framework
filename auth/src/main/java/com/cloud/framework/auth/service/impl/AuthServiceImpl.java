package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.service.AuthService;
import com.cloud.framework.auth.utils.JwtUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 系统授权ServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * @see com.cloud.framework.auth.service.AuthService#login(LoginUserRequest request)
     */
    @Override
    public String login(LoginUserRequest request) {
        //加密
        String encryptPassword = PasswordEncrypt.encryptSHA256(request.getPassWord());
        //使用security框架自带的验证token生成器  也可以自定义。
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), encryptPassword);
        Authentication authenticate = authenticationManager.authenticate(token);
        //放入Security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //生成Token
        String jwt = JwtUtil.createJWT(request.getEmail(), null);
        return jwt;
    }
}
