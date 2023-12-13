package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.AuthRoleCreateRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleDeleteRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleModifyRequest;

public interface AuthRoleService {
    /**
     * 新增角色
     * @param request 请求参数
     * */
    void addRole(AuthRoleCreateRequest request);

    /**
     * 修改角色
     * @param request
     */
    void modifyRole(AuthRoleModifyRequest request);


    /**
     * 删除角色
     * @param request
     */
    void deleteRole(AuthRoleDeleteRequest request);
}
