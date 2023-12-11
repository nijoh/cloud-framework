package com.cloud.framework.auth.service.impl;

import com.cloud.framework.utils.DefaultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.DeleteUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AccountUserMangeService;
import com.cloud.framework.auth.utils.convert.AccountUserConvert;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.auth.utils.TransactionService;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 账户信息ServiceImpl
 */
@Service
public class AccountUserMangeServiceImpl implements AccountUserMangeService {
    // 账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;

    // DB事务模版
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StaffInfoMapper staffInfoMapper;

    /**
     * @see AccountUserMangeService#saveAccountUser(RegistAccountUserRequest)
     */
    @Override
    public void saveAccountUser(RegistAccountUserRequest request) {
        AccountUser accountUser = AccountUserConvert.buildConverDOFromRequst(request);
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void processor() {
                int result = accountUserMapper.insertSelective(accountUser);
                AssertUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, "保存账户信息表失败");
            }
        });
    }

    /**
     * @see AccountUserMangeService#deleteAccountUser(DeleteUserRequest)
     */
    @Override
    public void deleteAccountUser(DeleteUserRequest request) {
        List<AccountUser> accountUsers = DefaultUtil.defaultEditArrayList(
            accountUserMapper.selectByExample(selectExampleByAccountUser(request.getEmailList())));
        List<String> accountUserIdList = accountUsers.stream().map(AccountUser::getId).collect(Collectors.toList());
        transactionService.processor(new TransactionProcessor() {

            @Override
            public void processor() {
                accountUserMapper.deleteListAccountUserById(accountUserIdList);
                staffInfoMapper.deleteListStaffInfoByUserId(accountUserIdList);
            }
        });
    }

    /**
     * 登录电子邮箱查询条件构造
     * 
     * @param emailList
     * @return
     */
    public Example selectExampleByAccountUser(List<String> emailList) {
        WeekendSqls<AccountUser> weekendSqls = WeekendSqls.<AccountUser>custom();
        weekendSqls.andIn(AccountUser::getEmail, emailList);
        return Example.builder(AccountUser.class).where(weekendSqls).build();
    }
}
