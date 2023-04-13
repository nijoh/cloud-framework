package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;

/**
 * 系统授权Service
 */
public interface AuthService {
    /**
     * 登录请求
     *
     * @param request 请求参数
     * @return if success return Token
     **/
    String login(LoginUserRequest request);

    /**
     * 注册用户
     *
     * @param request 请求参数
     */
    void regist(RegistAccountUserRequest request);

}
