package com.cloud.framework.auth.service.impl;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.cloud.framework.auth.dal.AuthMsMenuMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.AuthOperateContent;
import com.cloud.framework.auth.pojo.request.AuthMenuBaseRequest;
import com.cloud.framework.auth.pojo.request.AuthMenuCreateRequest;
import com.cloud.framework.auth.pojo.request.AuthMenuDeleteRequest;
import com.cloud.framework.auth.pojo.request.AuthMenuModifyRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthMenuQueryService;
import com.cloud.framework.auth.service.AuthMenuService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.stream.Collectors;

import static com.cloud.framework.model.common.constant.OperateTypeConstant.*;

/**
 * 菜单管理impl
 *
 * @author nijo_h
 * * @date 2023/3/30 11:41 下午
 */
@Service
public class AuthMenuServiceImpl extends AbstractBaseService implements AuthMenuService {
    @Autowired
    private AuthMsMenuMapper menuMapper;

    @Autowired
    private AuthMenuQueryService menuQueryService;

    /**
     * @see com.cloud.framework.auth.service.AuthMenuService#addMenu(AuthMenuCreateRequest)
     */
    @Override
    public void addMenu(AuthMenuCreateRequest request) {
        AuthMsMenu authMsMenu = this.converAuthMsMenu(request);
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public AuthOperateContent checkBiz() {
                if (request.getParentId() != 0) {
                    AuthMsMenu parentMenu = menuMapper.selectByPrimaryKey(request.getParentId());
                    AssertUtil.notNull(parentMenu, "未查询到父级菜单信息");
                    AssertUtil.equals(parentMenu.getStatus(), BaseStatusEnum.NORMAL.getCode(), "父级状态异常");
                }
                return new AuthOperateContent();
            }

            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_MENU_CREATE);
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = menuMapper.insertSelective(authMsMenu);
                AssertUtil.isTrue(result > 0, CloudConstant.DB_INSERT_ERROR);
            }
        });
    }

    /**
     * @see AuthMenuService#modifyMenu(AuthMenuModifyRequest)
     */
    @Override
    public void modifyMenu(AuthMenuModifyRequest request) {
        AuthMsMenu authMsMenu = this.converAuthMsMenu(request);
        authMsMenu.setId(request.getMenuId());
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public AuthOperateContent checkBiz() {
                if (request.getParentId() != 0) {
                    AuthMsMenu parentMenu = menuMapper.selectByPrimaryKey(request.getParentId());
                    AssertUtil.notNull(parentMenu, "未查询到父级菜单信息");
                    AssertUtil.equals(parentMenu.getStatus(), BaseStatusEnum.NORMAL.getCode(), "父级状态异常");
                }
                return new AuthOperateContent();
            }

            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_MENU_MODIFY);
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = menuMapper.updateByPrimaryKeySelective(authMsMenu);
                AssertUtil.isTrue(result > 0, CloudConstant.DB_MODIFY_ERROR);
            }
        });
    }

    /**
     *
     * @see AuthMenuService#deleteMenu(AuthMenuDeleteRequest)
     */
    @Override
    public void deleteMenu(AuthMenuDeleteRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public AuthOperateContent checkBiz() {
                List<AuthMsMenu> authMsMenus = menuQueryService.queryMenuListByMenusIds(request.getMenuId());
                AssertUtil.notEmpty(authMsMenus,"未查询到相关的菜单数据");
                //一级类目
                List<Integer> firstLevelMenuIds = authMsMenus.stream().filter(menu -> menu.getParentId() == 0).map(AuthMsMenu::getId).collect(Collectors.toList());
                //检测是否有子级菜单
                if(CollectionUtils.isNotEmpty(firstLevelMenuIds)){
                    firstLevelMenuIds.stream().peek(parentId->{
                        boolean checkResult = menuQueryService.checkFistLevelSubMenu(parentId);
                        AssertUtil.isFalse(checkResult,"当前菜单有子菜单使用");
                    }).close();
                }
                return new AuthOperateContent();
            }

            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_MENU_DELETE);
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = menuMapper.deleteByExample(deleteMenuExample(request.getMenuId()));
                AssertUtil.isTrue(result > 0, CloudConstant.DB_MODIFY_ERROR);
            }
        });
    }

    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AuthMsMenu 表对象
     */
    private AuthMsMenu converAuthMsMenu(AuthMenuBaseRequest request) {
        AuthMsMenu authMsMenu = new AuthMsMenu();
        BeanUtils.copyProperties(request, authMsMenu);
        return authMsMenu;
    }

    /**
     * 查询系统用户菜单 条件构造器
     *
     * @param
     * @return
     */
    public Example deleteMenuExample(List<Integer> menuIds) {
        WeekendSqls<AuthMsMenu> weekendSqls = WeekendSqls.<AuthMsMenu>custom();
        weekendSqls.andIn(AuthMsMenu::getId, menuIds);
        return Example.builder(AuthMsMenu.class).where(weekendSqls).build();
    }
}
