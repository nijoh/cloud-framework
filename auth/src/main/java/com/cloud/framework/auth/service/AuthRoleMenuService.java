package com.cloud.framework.auth.service;

import java.util.List;

/**
 * 系统角色菜单授权Service
 */
public interface AuthRoleMenuService {
    /**
     * 根据RoleId删除授权菜单
     * @param roleId
     */
    void deleteRoleMenuByRoleId(Integer roleId);

    /**
     * 添加角色授权菜单
     * @param roleId
     * @param menuIds
     */
    void createRoleMenu(Integer roleId, List<Integer> menuIds);
}
