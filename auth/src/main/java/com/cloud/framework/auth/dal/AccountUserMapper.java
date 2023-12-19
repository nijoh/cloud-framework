package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.model.auth.result.UserInfoDetailDTO;
import com.cloud.framework.model.auth.result.UserInfoListQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 账户信息Mapper
 * */
@Mapper
public interface AccountUserMapper extends MyBaseMapper<AccountUser> {
    void deleteListAccountUserById(List<String> userIdList);

    List<UserInfoListQueryDTO> queryPage(QueryUserReuqest reuqest);

    UserInfoDetailDTO queryUserInfoDetail(Integer staffId);

    UserInfoDetailDTO queryUserInfoDetailByEmail(String email);

}