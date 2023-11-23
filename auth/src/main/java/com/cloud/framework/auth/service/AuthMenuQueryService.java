package com.cloud.framework.auth.service;

import java.util.List;

import com.cloud.framework.model.auth.result.AuthMenuDTO;

/**
 * 菜单查询Service
 * @author nijo_h
 * * @date 2023/11/20 22:29
 */
public interface AuthMenuQueryService {
    List<AuthMenuDTO> queryMenuAll();
}
