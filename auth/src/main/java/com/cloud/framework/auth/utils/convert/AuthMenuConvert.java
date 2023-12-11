package com.cloud.framework.auth.utils.convert;

import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单数据转换
 * @author nijo_h
 * * @date 2023/11/20 22:41
 */
public class AuthMenuConvert {
    /**
     * 转换DTO
     * @param authMsMenu DO数据
     * @return
     */
    public static AuthMenuDTO converToDTOModel(AuthMsMenu authMsMenu) {
        AuthMenuDTO authMenuDTO = new AuthMenuDTO();
        authMenuDTO.setId(authMsMenu.getId());
        authMenuDTO.setParentId(authMsMenu.getParentId());
        authMenuDTO.setMenuName(authMsMenu.getMenuName());
        authMenuDTO.setMenuDesc(authMsMenu.getMenuDesc());
        authMenuDTO.setMenuUri(authMsMenu.getMenuUrl());
        authMenuDTO.setMenuCode(authMsMenu.getMenuCode());
        authMenuDTO.setMenuType(authMsMenu.getMenuType());
        return authMenuDTO;
    }

    /**
     * 转换List<DTO>
     * @param authMsMenuList List DO数据
     * @return
     */
    public static List<AuthMenuDTO> converToDTOFromList(List<AuthMsMenu> authMsMenuList) {
        List<AuthMenuDTO> authMenuDTOList =new ArrayList<>();
        for(AuthMsMenu authMsMenu:authMsMenuList){
            authMenuDTOList.add(converToDTOModel(authMsMenu));
        }
        return authMenuDTOList;
    }

    /**
     * 转换Tree结构
     * @param authMsMenu
     * @return
     */
    public static AuthMenuTreeDTO  converToTreeDTOModel(AuthMsMenu authMsMenu){
        AuthMenuTreeDTO authMenuTreeDTO = new AuthMenuTreeDTO();
        authMenuTreeDTO.setId(authMsMenu.getId());
        authMenuTreeDTO.setParentId(authMsMenu.getParentId());
        authMenuTreeDTO.setMenuName(authMsMenu.getMenuName());
        authMenuTreeDTO.setMenuDesc(authMsMenu.getMenuDesc());
        authMenuTreeDTO.setMenuUri(authMsMenu.getMenuUrl());
        authMenuTreeDTO.setMenuCode(authMsMenu.getMenuCode());
        authMenuTreeDTO.setMenuType(authMsMenu.getMenuType());
        return authMenuTreeDTO;
    }
}
