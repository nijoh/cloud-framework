package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.service.AccountUserQueryService;
import com.cloud.framework.auth.utils.convert.AccountUserConvert;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.auth.result.UserInfoDetailDTO;
import com.cloud.framework.model.auth.result.UserInfoListQueryDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询用户账户信息
 *
 * @author nijo_h
 * * @date 2023/11/14 19:53
 */
@Service
public class AccountUserQueryServiceImpl implements AccountUserQueryService {
    //账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private StaffInfoMapper staffInfoMapper;

    /**
     * @see AccountUserQueryService#findAccountUserByEmail(String)
     */
    @Override
    //@Cacheable(cacheNames="UserInfo",key = "#email")
    public AccountUserDTO findAccountUserByEmail(String email) {
        AccountUser accountUser = findAccountUser(email);
        return AccountUserConvert.converToDTOModel(accountUser);
    }

    /**
     * @see AccountUserQueryService#findAccountUser(String)
     */
    @Override
    public AccountUser findAccountUser(String email) {
        AccountUser accountUserRequest = new AccountUser();
        accountUserRequest.setEmail(email);
        AccountUser accountUser = accountUserMapper.selectOne(accountUserRequest);
        return accountUser;
    }

    /**
     * @see AccountUserQueryService#queryPage(QueryUserReuqest)
     */
    @Override
    public PageInfo queryPage(QueryUserReuqest queryUserReuqest) {
        //设置分页
        PageHelper.startPage(queryUserReuqest.getPageNum(), queryUserReuqest.getPageSize());
        List<UserInfoListQueryDTO> accountUserDTOS = accountUserMapper.queryPage(queryUserReuqest);
        PageInfo accountUserPageInfo = new PageInfo<>(accountUserDTOS);
        accountUserPageInfo.setList(accountUserDTOS);
        return accountUserPageInfo;
    }


    /**
     * @see AccountUserQueryService#queryUserInfoDetail(Integer)
     */
    @Override
    public UserInfoDetailDTO queryUserInfoDetail(Integer staffId) {
        return accountUserMapper.queryUserInfoDetail(staffId);
    }

    /**
     * @see AccountUserQueryService#queryUserInfoDetail(String)
     * */
    @Override
    public UserInfoDetailDTO queryUserInfoDetail(String emial) {
        return accountUserMapper.queryUserInfoDetailByEmail(emial);
    }

}
