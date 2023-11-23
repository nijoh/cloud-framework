package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.utils.AuthMenuConvert;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.framework.auth.dal.AuthMsMenuMapper;
import com.cloud.framework.auth.service.AuthMenuQueryService;

import java.util.List;

/**
 * 菜单管理Impl
 * @author nijo_h
 * * @date 2023/11/20 22:30
 */
@Service
public class AuthMenuQueryServiceImpl  implements AuthMenuQueryService {
    //菜单仓储
    @Autowired
    private AuthMsMenuMapper menuMapper;

    /**
     * @see AuthMenuQueryService#queryMenuAll()
     * */
    @Override
    public List<AuthMenuDTO> queryMenuAll() {
        List<AuthMsMenu> authMsMenus = menuMapper.selectAll();
        return AuthMenuConvert.converToDTOFromList(authMsMenus);
    }
}
