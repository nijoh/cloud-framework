package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthRoleStaffMapper;
import com.cloud.framework.auth.pojo.AuthRoleStaff;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.RoleStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * 员工角色实现
 */
@Service
public class RoleStaffServiceImpl extends AbstractBaseService implements RoleStaffService {
    /**
     * 员工角色仓储
     */
    @Autowired
    private AuthRoleStaffMapper roleStaffMapper;

    /**
     * @see RoleStaffService#checkRoleUsed(Integer)
     * */
    @Override
    public boolean checkRoleUsed(Integer roleId) {
        int result = roleStaffMapper.selectCountByExample(checkRoleUsedExample(roleId));
        return result>0;
    }


    /**
     * 校验角色是否有员工使用条件构造
     *
     * @param
     * @return
     */
    public Example checkRoleUsedExample(Integer roleId) {
        WeekendSqls<AuthRoleStaff> weekendSqls = WeekendSqls.<AuthRoleStaff>custom();
        weekendSqls.andEqualTo("roleId", roleId);
        return Example.builder(AuthRoleStaff.class).where(weekendSqls).build();
    }
}
