package com.cloud.framework.auth.controller;

import java.util.List;

import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.framework.auth.service.AuthMenuQueryService;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;

/**
 * 菜单查询Controller控制器
 * @author nijo_h
 * * @date 2023/11/20 22:28
 */
@RestController
@RequestMapping("/meuns")
public class AuthMenuQueryController {
    //菜单查询service
    @Autowired
    private AuthMenuQueryService authMenuQueryService;

    @GetMapping("/queryAll")
    public BaseResult<List<AuthMenuDTO>> queryAll(){
        BaseResult<List<AuthMenuDTO>> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setContent(authMenuQueryService.queryMenuAll());
            }
        });
        return result;
    }

    @GetMapping("/queryTree")
    public BaseResult<List<AuthMenuTreeDTO>> queryTree(){
        BaseResult<List<AuthMenuTreeDTO>> result =new BaseResult<>();
        System.out.println("当前登录的用户:"+AuthUserContextHolder.getCurrentUser().getUsername());
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setContent(authMenuQueryService.queryTree());
            }
        });
        return result;

    }
}
