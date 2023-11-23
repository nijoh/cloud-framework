package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.DeleteUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;


/**
 * 账户信息Service
 * */
public interface AccountUserMangeService {
    /**
     * 保存账户信息表
     *
     * @param request 请求参数
     */
    void saveAccountUser(RegistAccountUserRequest request);

    void deleteAccountUser(DeleteUserRequest request);
}
