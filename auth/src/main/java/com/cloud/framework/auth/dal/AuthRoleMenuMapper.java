package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.AuthRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统权限Mapper
 */
@Mapper
public interface AuthRoleMenuMapper extends MyBaseMapper<AuthRoleMenu> {

    int createRoleMenu(@Param("authRoleMenuList") List<AuthRoleMenu> authRoleMenuList);

    List<AuthMsMenu> queryAuthorize(@Param("roleId") Integer roleId);
}