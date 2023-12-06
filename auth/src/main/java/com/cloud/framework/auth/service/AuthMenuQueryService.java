package com.cloud.framework.auth.service;

import java.util.List;

import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;

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
    List<AuthMenuDTO> queryMenuAll();

    /**
     * 查询菜单 树级结构
     * */
    List<AuthMenuTreeDTO> queryTree();
}
