package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthRoleStaff;
import org.apache.ibatis.annotations.Mapper;


/**
 * 员工角色Mapper
 */
@Mapper
public interface AuthRoleStaffMapper extends MyBaseMapper<AuthRoleStaff> {
    /**
     * 删除员工授权角色
     * @param staffId
     */
    void deleteByStaffId(Integer staffId);

}