package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.AuthRoleMapper;
import com.cloud.framework.auth.dal.AuthRoleStaffMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.*;
import com.cloud.framework.auth.pojo.request.*;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AccountUserMangeService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import com.cloud.framework.utils.AssertUtil;
import com.cloud.framework.utils.DefaultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(), ACCOUNT_USER_CREATE);
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
            public void saveOrder(AuthOperateContent content) {
                operateOrderService.createOperateOrder(request.getBizNo(), ACCOUNT_USER_DELETE);
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
        transactionService.processor(new TransactionProcessor<AuthOperateContent<StaffInfo>>() {

            @Override
            public AuthOperateContent<StaffInfo> checkBiz() {
                AuthOperateContent content = new AuthOperateContent();
                Map<String, String> extendInfo = new HashMap<>();
                AuthRole authRole = roleMapper.selectByPrimaryKey(request.getRoleId());
                AssertUtil.notNull(authRole, "未找到角色信息");
                StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(request.getStaffId());
                AssertUtil.notNull(staffInfo, "未找到员工信息");
                extendInfo.put("roleName", authRole.getRoleName());
                content.setDataContent(staffInfo);
                content.setExtendInfo(extendInfo);
                return content;
            }

            @Override
            public void saveOrder(AuthOperateContent<StaffInfo> content) {
                operateOrderService.createOperateOrder(request.getBizNo(), AUTH_AUTHORIZE_ROLE);
            }

            @Override
            public void processor(AuthOperateContent<StaffInfo> content) {
                //删除已授权
                authRoleStaffMapper.deleteByStaffId(request.getStaffId());
                //新增
                int result = authRoleStaffMapper.insertSelective(buildAuthorizeRole(request));
                AssertUtil.isTrue(result > 0, "授权角色失败");
                //冗余员工信息
                StaffInfo staffInfo = content.getDataContent();
                staffInfo.setRoleName(content.getExtendInfo().get("roleName"));
                result = staffInfoMapper.updateByPrimaryKey(staffInfo);
                AssertUtil.isTrue(result > 0, "员工信息更新失败");
            }
        });
    }

    /**
     * @see AccountUserMangeService#modifyStaffInfo(StaffInfoModifyRequest)
     */
    @Override
    public void modifyStaffInfo(StaffInfoModifyRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent<StaffInfo>>() {

            @Override
            public AuthOperateContent<StaffInfo> checkBiz() {
                AuthOperateContent<StaffInfo> content = new AuthOperateContent<>();
                StaffInfo staffInfo = staffInfoMapper.selectByPrimaryKey(request.getStaffId());
                AssertUtil.notNull(staffInfo, "未找到员工信息");
                content.setDataContent(staffInfo);
                return content;
            }

            @Override
            public void saveOrder(AuthOperateContent<StaffInfo> content) {
                operateOrderService.createOperateOrder(request.getBizNo(), STAFF_INFO_MODIFY);
            }

            @Override
            public void processor(AuthOperateContent<StaffInfo> content) {
                int result = staffInfoMapper.updateByPrimaryKeySelective(buildStaffInfoUpdateSelective(request));
                AssertUtil.isTrue(result > 0, "更新员工信息失败");
                AccountUser accountUser = buildAccountUserUpdateSelective(request);
                accountUser.setId(content.getDataContent().getUid());
                result = accountUserMapper.updateByPrimaryKeySelective(accountUser);
                AssertUtil.isTrue(result > 0, "更新账户信息失败");
            }
        });
    }

    /**
     * @see AccountUserMangeService#freezeStaff(FreezeStaffRequest)
     */
    @Override
    public void freezeStaff(FreezeStaffRequest request) {
        transactionService.processor(new TransactionProcessor<AuthOperateContent<AccountUser>>() {

            @Override
            public AuthOperateContent checkBiz() {
                AuthOperateContent<AccountUser> content = new AuthOperateContent<>();
                //检测当前角色
                AssertUtil.isTrue(AuthUserContextHolder.isSuperAdministrator(), "当前用户无权限");
                //判断账户信息是否存在
                AccountUser accountUser = accountUserMapper.selectByPrimaryKey(request.getAccountId());
                AssertUtil.notNull(accountUser, "账户数据不存在");
                content.setDataContent(accountUser);
                return content;
            }

            @Override
            public void saveOrder(AuthOperateContent<AccountUser> content) {
                operateOrderService.createOperateOrder(request.getBizNo(), ACCOUNT_FREEZE_STAFF);
            }

            @Override
            public void processor(AuthOperateContent<AccountUser> content) {
                AccountUser accountUser = content.getDataContent();
                accountUser.setStatus(BaseStatusEnum.FREEZE.getCode());
                int result = accountUserMapper.updateByPrimaryKey(accountUser);
                AssertUtil.isTrue(result > 0, "冻结用户失败");
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

    /**
     * 构建更新员工信息请求
     *
     * @param request
     * @return
     */
    private StaffInfo buildStaffInfoUpdateSelective(StaffInfoModifyRequest request) {
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setId(request.getStaffId());
        staffInfo.setNickname(request.getNickName());
        staffInfo.setGender(request.getGender());
        return staffInfo;

    }

    /**
     * 构建更新账户信息请求
     *
     * @param request
     * @return
     */
    private AccountUser buildAccountUserUpdateSelective(StaffInfoModifyRequest request) {
        AccountUser accountUser = new AccountUser();
        accountUser.setPhone(request.getPhone());
        return accountUser;

    }
}
