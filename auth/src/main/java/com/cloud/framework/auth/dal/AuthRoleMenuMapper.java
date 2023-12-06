package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统权限Mapper
 * */
@Mapper
public interface AuthRoleMenuMapper extends MyBaseMapper<AuthRoleMenu> {

}