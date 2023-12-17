package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.RoleQueryRequest;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthRoleDTO;

import java.util.List;

/**
 * 角色查询Service
 */
public interface AuthRoleQueryService {

    /**
     * 条件查询系统角色
     * @param request
     * @return
     */
    List<AuthRoleDTO> queryRole(RoleQueryRequest request);

    /**
     * 查询授权资源
     * @param roleId 角色ID
     * @return
     */
    List<AuthMenuDTO> queryAuthorize(Integer roleId);
}
