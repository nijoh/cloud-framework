package com.cloud.framework.auth.controller;

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
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResult deleteAccountUser(@RequestBody @Validated DeleteUserRequest request){
        BaseResult result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                accountUserMangeService.deleteAccountUser(request);
            }
        });
        return result;
    }
}
