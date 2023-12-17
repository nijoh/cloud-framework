package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.AuthOperateContent;
import com.cloud.framework.auth.pojo.request.DeleteUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AccountUserMangeService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.utils.AssertUtil;
import com.cloud.framework.utils.DefaultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.stream.Collectors;

import static com.cloud.framework.auth.utils.convert.AccountUserConvert.buildConverDOFromRequst;
import static com.cloud.framework.model.common.constant.OperateTypeConstant.ACCOUNT_USER_CREATE;
import static com.cloud.framework.model.common.constant.OperateTypeConstant.ACCOUNT_USER_DELETE;

/**
 * 账户信息ServiceImpl
 */
@Service
public class AccountUserMangeServiceImpl extends AbstractBaseService implements AccountUserMangeService {
    // 账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;


    @Autowired
    private StaffInfoMapper staffInfoMapper;

    /**
     * @see AccountUserMangeService#saveAccountUser(RegistAccountUserRequest)
     */
    @Override
    public void saveAccountUser(RegistAccountUserRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public AuthOperateContent saveOrder() {
                operateOrderService.createOperateOrder(request.getBizNo(),ACCOUNT_USER_CREATE);
                return new AuthOperateContent();
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = accountUserMapper.insertSelective(buildConverDOFromRequst(request));
                AssertUtil.isTrue(result > 0,  "保存账户信息表失败");
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
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public AuthOperateContent saveOrder() {
                operateOrderService.createOperateOrder(request.getBizNo(),ACCOUNT_USER_DELETE);
                return new AuthOperateContent();
            }

            @Override
            public void processor(AuthOperateContent content) {
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
