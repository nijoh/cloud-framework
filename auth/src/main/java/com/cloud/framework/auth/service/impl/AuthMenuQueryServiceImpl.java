package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.utils.convert.AuthMenuConvert;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.framework.auth.dal.AuthMsMenuMapper;
import com.cloud.framework.auth.service.AuthMenuQueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

    /**
     * @see AuthMenuQueryService#queryTree()
     * */
    @Override
    public List<AuthMenuTreeDTO> queryTree() {
        List<AuthMenuTreeDTO> authMenuTreeDTOList = new ArrayList<>();
        List<AuthMsMenu> allMenus = menuMapper.selectAll();
        //获取所有一级
        allMenus.stream().filter(menu->menu.getParentId() == 0).forEach(authMsMenu -> {
            AuthMenuTreeDTO authMenuTreeDTO = recursionMenu(authMsMenu, allMenus);
            authMenuTreeDTOList.add(authMenuTreeDTO);
        });
        return authMenuTreeDTOList;
    }

    /**
     * 递归获取菜单
     * @param authMsMenu
     * @param allMenus
     * @return
     */
    private AuthMenuTreeDTO recursionMenu(AuthMsMenu authMsMenu,List<AuthMsMenu> allMenus){
        AuthMenuTreeDTO authMenuTreeDTO = AuthMenuConvert.converToTreeDTOModel(authMsMenu);
        Supplier<Stream<AuthMsMenu>> streamSupplier = () -> allMenus.stream().filter(menu -> menu.getParentId() == authMsMenu.getId());
        if(streamSupplier.get().count()>0){
            streamSupplier.get().forEach(x->{
                AuthMenuTreeDTO result = recursionMenu(x,allMenus);
                authMenuTreeDTO.getChildren().add(result);
            });
        }
        return authMenuTreeDTO;
    }
}
