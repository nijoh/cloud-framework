package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.AuthRoleAddRequest;

public interface AuthRoleService {
    /**
     * 新增角色
     * @param request 请求参数
     * */
    void addRole(AuthRoleAddRequest request);
}
