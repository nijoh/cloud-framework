package com.cloud.framework.auth.dal;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.AccountUser;

/**
 * 账户信息Mapper
 * */
@Mapper
public interface AccountUserMapper extends MyBaseMapper<AccountUser> {

    void deleteListAccountUserById(List<String> userIdList);
}