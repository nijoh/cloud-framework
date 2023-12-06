package com.cloud.framework.integrate.auth;

import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.auth.result.AuthMsDTO;
import com.cloud.framework.model.common.constant.CloudConstant;

import java.util.Optional;

/**
 * 获取当前登录用户信息
 */
public class AuthUserContextHolder {
    private AuthUserContextHolder() {
    }

    //用户信息
    private static final ThreadLocal<AccountUserDTO> threadLocal = new ThreadLocal<>();

    //所属域
    private static final ThreadLocal<AuthMsDTO> authMsThreadLocal = new ThreadLocal<>();

    /**
     * 保存当前用户
     *
     * @param accountUser
     */
    public static void putCurrentUser(AccountUserDTO accountUser) {
        threadLocal.set(accountUser);
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static AccountUserDTO getCurrentUser() {
        return threadLocal.get();
    }

    /**
     * 获取操作人员
     * @return
     */
    public static String getOperate(){
       return Optional.ofNullable(getCurrentUser()).orElseGet(()->{
            AccountUserDTO accountUserDTO = new AccountUserDTO();
            accountUserDTO.setUsername(CloudConstant.SYSTEM_OPERATE);
            return accountUserDTO;
        }).getUsername();
    }

    /**
     * 移除当前登录用户
     */
    public static void removeCurrentUser() {
        threadLocal.remove();
    }


    /**
     * 保存当前所属系统数据
     */
    public static void putCurrentAuthMs(AuthMsDTO authMs) {
        authMsThreadLocal.set(authMs);
    }

    /**
     * 获取系统所属域Domain
     */
    public static String getCurrentMsDomain() {
        return authMsThreadLocal.get().getMsDomain();
    }

    /**
     * 获取系统ID
     */
    public static Integer getCurrentMsId() {
        return authMsThreadLocal.get().getMsId();
    }

    /**
     * 删除当前所属系统数据
     */
    public static void removeCurrentAuthMs() {
        authMsThreadLocal.remove();
    }


}
