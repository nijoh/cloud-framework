package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统权限Mapper
 * */
@Mapper
public interface AuthItemMapper extends MyBaseMapper<AuthItem> {

}