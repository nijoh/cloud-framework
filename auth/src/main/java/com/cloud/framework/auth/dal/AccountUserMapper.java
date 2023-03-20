package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.MyBaseMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户信息Mapper
 * */
@Mapper
public interface AccountUserMapper extends MyBaseMapper<AccountUser> {

}