package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.request.*;


/**
 * 账户信息Service
 * */
public interface AccountUserMangeService {
    /**
     * 保存账户信息表
     *
     * @param request 请求参数
     */
    void saveAccountUser(RegistAccountUserRequest request);

    /**
     * 删除账户信息
     * @param request
     */
    void deleteAccountUser(DeleteUserRequest request);

    /**
     * 授权员工角色
     * @param request
     */
    void authorizeRole(AuthorizeRoleRequest request);


    /**
     * 修改员工信息
     * @param request
     */
    void modifyStaffInfo(StaffInfoModifyRequest request);

    /**
     * 冻结用户
     * @param request
     */
    void freezeStaff(FreezeStaffRequest request);
}
