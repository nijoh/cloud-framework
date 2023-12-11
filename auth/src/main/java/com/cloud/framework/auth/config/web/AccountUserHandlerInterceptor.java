package com.cloud.framework.auth.config.web;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.auth.service.AccountUserQueryService;
import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.common.constant.CloudConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器
 */
@Component
public class AccountUserHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private AccountUserQueryService accountUserQueryService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //设置当前登录用户
        String accountUserEmail = request.getHeader(CloudConstant.REQUEST_HEADER_ACCOUNTUSEREMAIL);
        if (StringUtils.isNotBlank(accountUserEmail)) {
            AccountUserDTO accountUserByEmail = accountUserQueryService.findAccountUserByEmail(accountUserEmail);
            AuthUserContextHolder.putCurrentUser(accountUserByEmail);
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //清理当前用户
        AuthUserContextHolder.removeCurrentUser();
    }
}
