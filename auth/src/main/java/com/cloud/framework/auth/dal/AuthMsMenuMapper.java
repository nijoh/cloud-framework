package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统菜单Mapper
 */
@Mapper
public interface AuthMsMenuMapper extends MyBaseMapper<AuthMsMenu> {

}