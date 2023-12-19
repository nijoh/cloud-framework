package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.AuthRoleMapper;
import com.cloud.framework.auth.dal.AuthRoleStaffMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.*;
import com.cloud.framework.auth.pojo.request.AuthorizeRoleRequest;
import com.cloud.framework.auth.pojo.request.DeleteUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AccountUserMangeService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import com.cloud.framework.utils.AssertUtil;
import com.cloud.framework.utils.DefaultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;
import java.util.stream.Collectors;

import static com.cloud.framework.auth.utils.convert.AccountUserConvert.buildConverDOFromRequst;
import static com.cloud.framework.model.common.constant.OperateTypeConstant.*;

/**
 * 账户信息ServiceImpl
 */
@Service
public class AccountUserMangeServiceImpl extends AbstractBaseService implements AccountUserMangeService {
    // 账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;

    //员工仓储
    @Autowired
    private StaffInfoMapper staffInfoMapper;

    //角色仓储
    @Autowired
    private AuthRoleMapper roleMapper;

    //员工授权角色仓储
    @Autowired
    private AuthRoleStaffMapper authRoleStaffMapper;

    /**
     * @see AccountUserMangeService#saveAccountUser(RegistAccountUserRequest)
     */
    @Override
    public void saveAccountUser(RegistAccountUserRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {
            @Override
            public AuthOperateContent saveOrder() {
                operateOrderService.createOperateOrder(request.getBizNo(), ACCOUNT_USER_CREATE);
                return new AuthOperateContent();
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = accountUserMapper.insertSelective(buildConverDOFromRequst(request));
                AssertUtil.isTrue(result > 0, "保存账户信息表失败");
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
                operateOrderService.createOperateOrder(request.getBizNo(), ACCOUNT_USER_DELETE);
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
     * @see AccountUserMangeService#authorizeRole(AuthorizeRoleRequest)
     */
    @Override
    public void authorizeRole(AuthorizeRoleRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public void checkBiz() {
                AuthRole authRole = roleMapper.selectByPrimaryKey(request.getRoleId());
                AssertUtil.notNull(authRole, "未找到角色信息");
                StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(request.getStaffId());
                AssertUtil.notNull(staffInfo, "未找到员工信息");
            }

            @Override
            public AuthOperateContent saveOrder() {
                operateOrderService.createOperateOrder(request.getBizNo(), AUTH_AUTHORIZE_ROLE);
                return new AuthOperateContent();
            }

            @Override
            public void processor(AuthOperateContent content) {
                //删除已授权
                authRoleStaffMapper.deleteByStaffId(request.getStaffId());
                //新增
                int result = authRoleStaffMapper.insertSelective(buildAuthorizeRole(request));
                AssertUtil.isTrue(result > 0, "授权角色失败");

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

    /**
     * 构建授权角色信息
     *
     * @param request
     * @return
     */
    private AuthRoleStaff buildAuthorizeRole(AuthorizeRoleRequest request) {
        AuthRoleStaff roleStaff = new AuthRoleStaff();
        roleStaff.setRoleId(request.getRoleId());
        roleStaff.setStaffId(request.getStaffId());
        roleStaff.setStatus(BaseStatusEnum.NORMAL.getCode());
        return roleStaff;
    }
}
