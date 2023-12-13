package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthRoleMapper;
import com.cloud.framework.auth.pojo.AuthRole;
import com.cloud.framework.auth.pojo.request.AuthRoleBaseRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleCreateRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleDeleteRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleModifyRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthRoleService;
import com.cloud.framework.auth.service.RoleStaffService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @see AuthRoleService#addRole(AuthRoleCreateRequest)
     */
    @Override
    public void addRole(AuthRoleCreateRequest request) {
        AuthRole authRole = converAuthRole(request);
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void processor() {
                int result = roleMapper.insertSelective(authRole);
                AssertUtil.isTrue(result > 0, CloudConstant.DB_INSERT_ERROR);

            }
        });
    }

    /**
     * @see AuthRoleService#modifyRole(AuthRoleModifyRequest)
     * @param request
     */
    @Override
    public void modifyRole(AuthRoleModifyRequest request) {
        AuthRole saveAuthRole = converAuthRole(request);
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void checkBiz() {
                AuthRole authRole = roleMapper.selectByPrimaryKey(request.getRoleId());
                AssertUtil.notNull(authRole,"未获取到相关角色");
            }

            @Override
            public void processor() {
                saveAuthRole.setId(request.getRoleId());
                int result = roleMapper.updateByPrimaryKeySelective(saveAuthRole);
                AssertUtil.isTrue(result > 0,  CloudConstant.DB_MODIFY_ERROR);
            }
        });
    }

    /**
     * @see AuthRoleService#deleteRole(AuthRoleDeleteRequest)
     *
     */
    @Override
    public void deleteRole(AuthRoleDeleteRequest request) {
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void checkBiz() {
                AuthRole authRole = roleMapper.selectByPrimaryKey(request.getRoleId());
                AssertUtil.notNull(authRole,"未获取到相关角色");
                boolean roleUsedResult = roleStaffService.checkRoleUsed(request.getRoleId());
                AssertUtil.isFalse(roleUsedResult,"当前有用户关联此角色,解除绑定后方可删除");
            }

            @Override
            public void processor() {
                int result = roleMapper.deleteByPrimaryKey(request.getRoleId());
                AssertUtil.isTrue(result > 0, CloudConstant.DB_DELETE_ERROR);
            }
        });
    }


    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AuthMsMenu 表对象
     */
    private AuthRole converAuthRole(AuthRoleBaseRequest request){
        AuthRole authRole = new AuthRole();
        authRole.setRoleName(request.getRoleName());
        authRole.setRoleDesc(request.getRoleDesc());
        authRole.setStatus(request.getStatus());
        return authRole;
    }
}
