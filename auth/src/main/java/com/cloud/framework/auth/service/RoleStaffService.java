package com.cloud.framework.auth.service;

/**
 * 员工角色Service
 */
public interface RoleStaffService {

    /**
     * 校验角色是否有员工使用
     * @param roleId
     * @return
     */
    boolean checkRoleUsed(Integer roleId);
}
