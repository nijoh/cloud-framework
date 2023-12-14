package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.AuthMenuCreateRequest;
import com.cloud.framework.auth.pojo.request.AuthMenuDeleteRequest;
import com.cloud.framework.auth.pojo.request.AuthMenuModifyRequest;

/**
 * 菜单管理Service
 * @author nijo_h
 * * @date 2023/3/30 11:39 下午
 */
public interface AuthMenuService {
    /**
     * 新增菜单
     * @param request 请求参数
     * */
    void addMenu(AuthMenuCreateRequest request);

    /**
     * 修改菜单
     * @param request
     */
    void modifyMenu(AuthMenuModifyRequest request);


    /**
     * 删除菜单
     * @param request
     */
    void deleteMenu(AuthMenuDeleteRequest request);
}
