package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.auth.utils.AccountUserConvert;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.auth.utils.TransactionService;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AsserUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 账户信息ServiceImpl
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {
    //账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;

    //DB事务模版
    @Autowired
    private TransactionService transactionService;

    /**
     * @see com.cloud.framework.auth.service.AccountUserService#findAccountUserByEmail(String)
     */
    @Override
    @Cacheable(cacheNames="UserInfo",key = "#email")
    public AccountUser findAccountUserByEmail(String email) {
        AccountUser accountUser = new AccountUser();
        accountUser.setEmail(email);
        return accountUserMapper.selectOne(accountUser);
    }

    /**
     * @see com.cloud.framework.auth.service.AccountUserService#saveAccountUser(RegistAccountUserRequest)
     */
    @Override
    public void saveAccountUser(RegistAccountUserRequest request) {
        AccountUser accountUser = AccountUserConvert.buildConverDOFromRequst(request);
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void processor() {
                int result = accountUserMapper.insertSelective(accountUser);
                AsserUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, "保存账户信息表失败");
            }
        });
    }


    /**
     * @see AccountUserService#queryPage(QueryUserReuqest)
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

    //条件构造
    public Example createExample(QueryUserReuqest queryUserReuqest){
        Example example=new Example(AccountUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(queryUserReuqest!=null){
            // 用户名
            if(StringUtils.isNotBlank(queryUserReuqest.getUsername())){
                criteria.andEqualTo("username",queryUserReuqest.getUsername());
            }
            if(StringUtils.isNotBlank(queryUserReuqest.getCreateTime())){
                criteria.andEqualTo("createTime",queryUserReuqest.getCreateTime());
            }


        }
        return example;
    }

}
