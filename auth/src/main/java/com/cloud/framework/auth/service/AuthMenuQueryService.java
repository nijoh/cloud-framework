package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.request.AuthMenuQueryRequest;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;

import java.util.List;

/**
 * 菜单查询Service
 * @author nijo_h
 * * @date 2023/11/20 22:29
 */
public interface AuthMenuQueryService {
    /**
     * 查询所有菜单
     * @return
     */
    List<AuthMenuDTO> queryMenu(AuthMenuQueryRequest request);

    /**
     * 查询菜单 树级结构
     * */
    List<AuthMenuTreeDTO> queryTree();

    /**
     * 根据批量菜单ID查询菜单
     * @param menuIds
     * @return
     */
    List<AuthMsMenu> queryMenuListByMenusIds(List<Integer> menuIds);

    /**
     * 检查一级类目是否有子菜单
     * @param parentId 父级菜单ID
     * */
    boolean checkFistLevelSubMenu(Integer parentId);
}
