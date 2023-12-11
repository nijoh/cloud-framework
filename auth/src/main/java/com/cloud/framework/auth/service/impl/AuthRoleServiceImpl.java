package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthRoleMapper;
import com.cloud.framework.auth.pojo.AuthRole;
import com.cloud.framework.auth.pojo.request.AuthRoleAddRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthRoleService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
     * @see AuthRoleService#addRole(AuthRoleAddRequest)
     */
    @Override
    public void addRole(AuthRoleAddRequest request) {
        AuthRole authRole = converAuthRole(request);
        authRole.setOperator("123");
        authRole.setMsDomain("21312");
        authRole.setCreateTime(LocalDateTime.now());
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void processor() {
                int result = roleMapper.insertSelective(authRole);
                AssertUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, CloudConstant.DB_INSERT_ERROR);

            }
        });
    }


    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AuthMsMenu 表对象
     */
    private AuthRole converAuthRole(AuthRoleAddRequest request){
        AuthRole authRole = new AuthRole();
        authRole.setRoleName(request.getRoleName());
        authRole.setRoleDesc(request.getRoleDesc());
        authRole.setStatus(request.getStatus());
        return authRole;
    }
}
