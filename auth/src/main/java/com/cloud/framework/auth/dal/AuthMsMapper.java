package com.cloud.framework.auth.dal;


import com.cloud.framework.auth.config.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthMs;
import org.apache.ibatis.annotations.Mapper;


/**
 * 系统表Mapper
 */
@Mapper
public interface AuthMsMapper extends MyBaseMapper<AuthMs> {

}