package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthRoleMapper;
import com.cloud.framework.auth.pojo.AuthOperateContent;
import com.cloud.framework.auth.pojo.AuthRole;
import com.cloud.framework.auth.pojo.request.*;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthRoleMenuService;
import com.cloud.framework.auth.service.AuthRoleService;
import com.cloud.framework.auth.service.RoleStaffService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cloud.framework.model.common.constant.OperateTypeConstant.*;

/**
 * 系统角色Service Impl
 */
@Service
public class AuthRoleServiceImpl extends AbstractBaseService implements AuthRoleService {

    /**
     * 角色仓储
     */
    @Autowired
    private AuthRoleMapper roleMapper;

    /**
     * 员工角色Service
     */
    @Autowired
    private RoleStaffService roleStaffService;

    /**
     * 角色菜单授权Service
     */
    @Autowired
    private AuthRoleMenuService roleMenuService;


    /**
     * @see AuthRoleService#addRole(AuthRoleCreateRequest)
     */
    @Override
    public void addRole(AuthRoleCreateRequest request) {
        AuthRole authRole = converAuthRole(request);
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_ROLE_CREATE);
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = roleMapper.insertSelective(authRole);
                AssertUtil.isTrue(result > 0, CloudConstant.DB_INSERT_ERROR);

            }
        });
    }

    /**
     * @param request
     * @see AuthRoleService#modifyRole(AuthRoleModifyRequest)
     */
    @Override
    public void modifyRole(AuthRoleModifyRequest request) {
        AuthRole saveAuthRole = converAuthRole(request);
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public AuthOperateContent checkBiz() {
                AuthRole authRole = roleMapper.selectByPrimaryKey(request.getRoleId());
                AssertUtil.notNull(authRole, "未获取到相关角色");
                return new AuthOperateContent();
            }

            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_ROLE_MODIFY);
            }

            @Override
            public void processor(AuthOperateContent content) {
                saveAuthRole.setId(request.getRoleId());
                int result = roleMapper.updateByPrimaryKeySelective(saveAuthRole);
                AssertUtil.isTrue(result > 0, CloudConstant.DB_MODIFY_ERROR);
            }
        });
    }

    /**
     * @see AuthRoleService#deleteRole(AuthRoleDeleteRequest)
     */
    @Override
    public void deleteRole(AuthRoleDeleteRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public AuthOperateContent checkBiz() {
                AuthRole authRole = roleMapper.selectByPrimaryKey(request.getRoleId());
                AssertUtil.notNull(authRole, "未获取到相关角色");
                boolean roleUsedResult = roleStaffService.checkRoleUsed(request.getRoleId());
                AssertUtil.isFalse(roleUsedResult, "当前有用户关联此角色,解除绑定后方可删除");
                return new AuthOperateContent();
            }

            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_ROLE_DELETE);
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = roleMapper.deleteByPrimaryKey(request.getRoleId());
                AssertUtil.isTrue(result > 0, CloudConstant.DB_DELETE_ERROR);
            }
        });
    }

    /**
     * @see AuthRoleService#authorizeMenus(AuthorizeMenusRequest)
     */
    @Override
    public void authorizeMenus(AuthorizeMenusRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(),AUTH_ROLE_MENU_CREATE);
            }

            @Override
            public void processor(AuthOperateContent content) {
                //先删除已有的
                roleMenuService.deleteRoleMenuByRoleId(request.getRoleId());
                //新增本次授权的
                roleMenuService.createRoleMenu(request.getRoleId(),request.getMenuIds());
            }
        });
    }


    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AuthMsMenu 表对象
     */
    private AuthRole converAuthRole(AuthRoleBaseRequest request) {
        AuthRole authRole = new AuthRole();
        authRole.setRoleName(request.getRoleName());
        authRole.setRoleDesc(request.getRoleDesc());
        authRole.setStatus(request.getStatus());
        return authRole;
    }
}
