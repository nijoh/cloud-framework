package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户信息查询
 * @author nijo_h
 * * @date 2023/11/2 22:15
 */
@RequestMapping("/accountUser")
@RestController
public class AccountUserController {
    //账号用户Service
    @Autowired
    private AccountUserService accountUserService;


    @PostMapping("/queryPage")
    public BaseResult<PageInfo<AccountUserDTO>> queryUserPage(@RequestBody QueryUserReuqest queryUserReuqest){
        BaseResult<PageInfo<AccountUserDTO>> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setData(accountUserService.queryPage(queryUserReuqest));
            }
        });
        return result;
    }
}
