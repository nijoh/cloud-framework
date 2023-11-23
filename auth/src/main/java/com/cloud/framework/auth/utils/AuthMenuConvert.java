package com.cloud.framework.auth.utils;

import java.util.ArrayList;
import java.util.List;

import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.model.auth.result.AuthMenuDTO;

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
        authMenuDTO.setMsId(authMsMenu.getMsId());
        authMenuDTO.setParentId(authMsMenu.getParentId());
        authMenuDTO.setMenuName(authMsMenu.getMenuName());
        authMenuDTO.setMenuDesc(authMsMenu.getMenuDesc());
        authMenuDTO.setMenuUri(authMsMenu.getMenuUri());
        authMenuDTO.setMenuCode(authMsMenu.getMenuCode());
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
}
