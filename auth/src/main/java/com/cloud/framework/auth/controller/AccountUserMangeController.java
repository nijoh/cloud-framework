package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.AuthorizeRoleRequest;
import com.cloud.framework.auth.pojo.request.FreezeStaffRequest;
import com.cloud.framework.auth.pojo.request.StaffInfoModifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.framework.auth.pojo.request.DeleteUserRequest;
import com.cloud.framework.auth.service.AccountUserMangeService;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;

/**
 * @author nijo_h
 * * @date 2023/11/14 20:12
 */
@RequestMapping("/account/user")
@RestController
public class AccountUserMangeController {
    @Autowired
    private AccountUserMangeService accountUserMangeService;


    /**
     * 删除用户账户
     *
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResult deleteAccountUser(@RequestBody @Validated DeleteUserRequest request) {
        BaseResult result = new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                accountUserMangeService.deleteAccountUser(request);
            }
        });
        return result;
    }


    /**
     * 授权员工角色
     *
     * @param request
     */
    @PostMapping("/authorizeRole")
    public BaseResult authorizeRole(@RequestBody @Validated AuthorizeRoleRequest request) {
        BaseResult result = new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                accountUserMangeService.authorizeRole(request);
            }
        });
        return result;
    }

    /**
     * 修改员工信息
     *
     * @param request
     */
    @PostMapping("/modifyStaff")
    public BaseResult modifyStaffInfo(@RequestBody @Validated StaffInfoModifyRequest request) {
        BaseResult result = new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                accountUserMangeService.modifyStaffInfo(request);
            }
        });
        return result;
    }

    /**
     * 冻结用户
     *
     * @param request
     */
    @PostMapping("/freezeStaff")
    public BaseResult freezeStaff(@RequestBody @Validated FreezeStaffRequest request) {
        BaseResult result = new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                accountUserMangeService.freezeStaff(request);
            }
        });
        return result;
    }
}
