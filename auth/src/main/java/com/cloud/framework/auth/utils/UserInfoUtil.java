package com.cloud.framework.auth.utils;

import com.cloud.framework.auth.pojo.AccountUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前用户信息
 * @author nijo_h
 * * @date 2023/4/21 23:17
 */
public class UserInfoUtil {
    /**
     * 获取当前登录用户信息
     * */
    public static AccountUser getUser(){
        return (AccountUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
