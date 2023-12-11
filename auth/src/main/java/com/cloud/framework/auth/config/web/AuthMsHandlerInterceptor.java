package com.cloud.framework.auth.config.web;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.auth.service.AuthMsQueryService;
import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import com.cloud.framework.model.auth.result.AuthMsDTO;
import com.cloud.framework.model.common.constant.CloudConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所属系统拦截器
 */
@Component
public class AuthMsHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthMsQueryService msQueryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取系统所属域
        String msDomain = request.getHeader(CloudConstant.REQUEST_HEADER_DOMAIN);
        if (StringUtils.isNotBlank(msDomain)) {
            AuthMsDTO authMs = msQueryService.queryAuthMsByDomain(msDomain);
            AuthUserContextHolder.putCurrentAuthMs(authMs);
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthUserContextHolder.removeCurrentAuthMs();
    }
}
