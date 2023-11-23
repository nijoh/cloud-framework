package com.cloud.framework.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.service.AccountUserQueryService;
import com.cloud.framework.auth.utils.AccountUserConvert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * 查询用户账户信息
 * @author nijo_h
 * * @date 2023/11/14 19:53
 */
@Service
public class AccountUserQueryServiceImpl implements AccountUserQueryService {
    //账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;


    /**
     * @see AccountUserQueryService#findAccountUserByEmail(String)
     */
    @Override
    //@Cacheable(cacheNames="UserInfo",key = "#email")
    public AccountUser findAccountUserByEmail(String email) {
        AccountUser accountUser = new AccountUser();
        accountUser.setEmail(email);
        return accountUserMapper.selectOne(accountUser);
    }

    /**
     * @see AccountUserQueryService#queryPage(QueryUserReuqest)
     */
    @Override
    public PageInfo queryPage(QueryUserReuqest queryUserReuqest) {
        //设置分页
        PageHelper.startPage(queryUserReuqest.getPageNum(),queryUserReuqest.getPageSize());
        //条件查询
        List<AccountUser> accountUsers = accountUserMapper.selectByExample(createExample(queryUserReuqest));
        PageInfo accountUserPageInfo = new PageInfo<>(accountUsers);
        accountUserPageInfo.setList(AccountUserConvert.converToDTOFromList(accountUsers));
        return accountUserPageInfo;
    }

    /**
     * 条件构造
     * @param queryUserReuqest 请求
     * @return
     */
    public Example createExample(QueryUserReuqest queryUserReuqest){
        WeekendSqls<AccountUser> weekendSqls=WeekendSqls.<AccountUser>custom();
        //条件筛选
        if(queryUserReuqest!=null){
            // 用户名
            if(StringUtils.isNotBlank(queryUserReuqest.getUsername())){
                weekendSqls.andEqualTo(AccountUser::getUsername,queryUserReuqest.getUsername());
            }
            if(StringUtils.isNotBlank(queryUserReuqest.getCreateTime())){
                weekendSqls.andEqualTo(AccountUser::getCreateTime,queryUserReuqest.getCreateTime());
            }
        }
        return Example.builder(AccountUser.class).where(weekendSqls).build();
    }
}
