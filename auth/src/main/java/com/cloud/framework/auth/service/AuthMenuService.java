package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.AddAuthMenuRequest;

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
    void addMenu(AddAuthMenuRequest request);
}
