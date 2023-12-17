package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthRoleMenuMapper;
import com.cloud.framework.auth.pojo.AuthOperateContent;
import com.cloud.framework.auth.pojo.AuthRoleMenu;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthRoleMenuService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色菜单授权 Impl
 */
@Service
public class AuthRoleMenuServiceImpl extends AbstractBaseService implements AuthRoleMenuService {

    @Autowired
    private AuthRoleMenuMapper roleMenuMapper;

    /**
     * @see AuthRoleMenuService#deleteRoleMenuByRoleId(Integer)
     */
    @Override
    public void deleteRoleMenuByRoleId(Integer roleId) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public void processor(AuthOperateContent content) {
                roleMenuMapper.deleteByExample(deleteRoleMenuByRoleIdExample(roleId));
            }
        });
    }

    /**
     * @see AuthRoleMenuService#createRoleMenu(Integer, List)
     */
    @Override
    public void createRoleMenu(Integer roleId, List<Integer> menuIds) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public void processor(AuthOperateContent content) {
                List<AuthRoleMenu> authRoleMenus = buildDoList(roleId, menuIds);
                roleMenuMapper.createRoleMenu(authRoleMenus);
            }
        });
    }

    /**
     * 查询系统用户角色 条件构造器
     *
     * @param
     * @return
     */
    public Example deleteRoleMenuByRoleIdExample(Integer roleId) {
        WeekendSqls<AuthRoleMenu> weekendSqls = WeekendSqls.<AuthRoleMenu>custom();
        weekendSqls.andEqualTo(AuthRoleMenu::getRoleId, roleId);
        return Example.builder(AuthRoleMenu.class).where(weekendSqls).build();
    }


    List<AuthRoleMenu> buildDoList(Integer roleId, List<Integer> menuIds) {
        List<AuthRoleMenu> authRoleMenuList = new ArrayList<>();
        for (Integer menuId : menuIds) {
            AuthRoleMenu authRoleMenu = new AuthRoleMenu();
            authRoleMenu.setRoleId(roleId);
            authRoleMenu.setMenuId(menuId);
            authRoleMenu.setStatus(BaseStatusEnum.NORMAL.getCode());
            authRoleMenuList.add(authRoleMenu);
        }
        return authRoleMenuList;
    }
}
