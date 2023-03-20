package com.cloud.framework.auth.dal;


import com.cloud.framework.auth.config.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthRole;
import org.apache.ibatis.annotations.Mapper;


/**
 * 系统角色Mapper
 */
@Mapper
public interface AuthRoleMapper extends MyBaseMapper<AuthRole> {

}