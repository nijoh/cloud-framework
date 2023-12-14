package com.cloud.framework.auth.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.auth.dal.AuthMsMenuMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.request.AuthMenuQueryRequest;
import com.cloud.framework.auth.service.AuthMenuQueryService;
import com.cloud.framework.auth.utils.convert.AuthMenuConvert;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;
import com.cloud.framework.utils.CloudStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 菜单管理Impl
 *
 * @author nijo_h
 * * @date 2023/11/20 22:30
 */
@Service
public class AuthMenuQueryServiceImpl implements AuthMenuQueryService {
    //菜单仓储
    @Autowired
    private AuthMsMenuMapper menuMapper;

    /**
     * @see AuthMenuQueryService#queryMenu(AuthMenuQueryRequest request)
     */
    @Override
    public List<AuthMenuDTO> queryMenu(AuthMenuQueryRequest request) {
        List<AuthMsMenu> authMsMenus = menuMapper.selectByExample(queryMenuExample(request));
        return AuthMenuConvert.converToDTOFromList(authMsMenus);
    }

    /**
     * @see AuthMenuQueryService#queryTree()
     */
    @Override
    public List<AuthMenuTreeDTO> queryTree() {
        List<AuthMenuTreeDTO> authMenuTreeDTOList = new ArrayList<>();
        List<AuthMsMenu> allMenus = menuMapper.selectAll();
        //获取所有一级
        allMenus.stream().filter(menu -> menu.getParentId() == 0).forEach(authMsMenu -> {
            AuthMenuTreeDTO authMenuTreeDTO = recursionMenu(authMsMenu, allMenus);
            authMenuTreeDTOList.add(authMenuTreeDTO);
        });
        return authMenuTreeDTOList;
    }

    /**
     * @see AuthMenuQueryService#queryMenuListByMenusIds(List)
     */
    @Override
    public List<AuthMsMenu> queryMenuListByMenusIds(List<Integer> menuIds) {
        return menuMapper.selectByExample(queryMenuListByMenusIdsExample(menuIds));
    }


    /**
     * @see AuthMenuQueryService#checkFistLevelSubMenu(Integer)
     */
    @Override
    public boolean checkFistLevelSubMenu(Integer parentId) {
        return menuMapper.selectCountByExample(checkFistLevelSubMenuExample(parentId)) > 0;
    }

    /**
     * 递归获取菜单
     *
     * @param authMsMenu
     * @param allMenus
     * @return
     */
    private AuthMenuTreeDTO recursionMenu(AuthMsMenu authMsMenu, List<AuthMsMenu> allMenus) {
        AuthMenuTreeDTO authMenuTreeDTO = AuthMenuConvert.converToTreeDTOModel(authMsMenu);
        Supplier<Stream<AuthMsMenu>> streamSupplier = () -> allMenus.stream().filter(menu -> menu.getParentId() == authMsMenu.getId());
        if (streamSupplier.get().count() > 0) {
            streamSupplier.get().forEach(x -> {
                AuthMenuTreeDTO result = recursionMenu(x, allMenus);
                authMenuTreeDTO.getChildren().add(result);
            });
        }
        return authMenuTreeDTO;
    }

    /**
     * 查询系统用户菜单 条件构造器
     *
     * @param
     * @return
     */
    public Example queryMenuExample(AuthMenuQueryRequest request) {
        WeekendSqls<AuthMsMenu> weekendSqls = WeekendSqls.<AuthMsMenu>custom();
        if (StringUtils.isNotBlank(request.getMenuName())) {
            weekendSqls.andLike(AuthMsMenu::getMenuName, CloudStringUtils.GeneratLikeString(request.getMenuName()));
        }
        if (StringUtils.isNotBlank(request.getStatus())) {
            weekendSqls.andEqualTo(AuthMsMenu::getStatus, request.getStatus());
        }
        if (StringUtils.isNotBlank(request.getMenuType())) {
            weekendSqls.andEqualTo(AuthMsMenu::getMenuType, request.getMenuType());
        }
        return Example.builder(AuthMsMenu.class).where(weekendSqls).build();
    }

    /**
     * 查询系统用户菜单 条件构造器
     *
     * @param
     * @return
     */
    public Example queryMenuListByMenusIdsExample(List<Integer> menuIds) {
        WeekendSqls<AuthMsMenu> weekendSqls = WeekendSqls.<AuthMsMenu>custom();
        weekendSqls.andIn(AuthMsMenu::getId, menuIds);
        return Example.builder(AuthMsMenu.class).where(weekendSqls).build();
    }

    /**
     * 查询系统用户菜单 条件构造器
     *
     * @param
     * @return
     */
    public Example checkFistLevelSubMenuExample(Integer parentId) {
        WeekendSqls<AuthMsMenu> weekendSqls = WeekendSqls.<AuthMsMenu>custom();
        weekendSqls.andEqualTo(AuthMsMenu::getParentId, parentId);
        return Example.builder(AuthMsMenu.class).where(weekendSqls).build();
    }
}
