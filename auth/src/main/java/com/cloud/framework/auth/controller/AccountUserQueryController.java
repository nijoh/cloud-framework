package com.cloud.framework.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.service.AccountUserQueryService;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;
import com.github.pagehelper.PageInfo;

/**
 * 账户信息查询
 * @author nijo_h
 * * @date 2023/11/2 22:15
 */
@RequestMapping("/account/user")
@RestController
public class AccountUserQueryController {
    //用户查询Service
    @Autowired
    private AccountUserQueryService accountUserQueryService;

    /**
     * 分页查询用户账户信息
     * @param queryUserReuqest 查询请求参数
     * @return
     */
    @PostMapping("/queryPage")
    public BaseResult<PageInfo<AccountUserDTO>> queryUserPage(@RequestBody QueryUserReuqest queryUserReuqest){
        BaseResult<PageInfo<AccountUserDTO>> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setContent(accountUserQueryService.queryPage(queryUserReuqest));
            }
        });
        return result;
    }




}
