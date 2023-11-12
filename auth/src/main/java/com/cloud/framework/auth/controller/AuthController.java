package com.cloud.framework.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AuthService;
import com.cloud.framework.model.auth.result.LoginResultDTO;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;

/**
 * 系统授权Controller
 * */
@RestController
@RequestMapping("/auth")
public class AuthController {
    //系统授权Service
    @Autowired
    private AuthService authService;

    /**
     * 登录方法
     * @param request 请求参数
     * @return Result<String> 返回结果
     * */
    @PostMapping("/login")
    public BaseResult<LoginResultDTO> login(@RequestBody @Validated LoginUserRequest request){
        BaseResult<LoginResultDTO> baseResult =new BaseResult<>();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void processor() {
                LoginResultDTO token = authService.login(request);
                baseResult.setContent(token);
            }
        });
        return baseResult;
    }

    /**
     * 用户注册
     * */
    @PostMapping("/regist")
    public BaseResult regist(@RequestBody @Validated RegistAccountUserRequest request){
        BaseResult baseResult =new BaseResult<>();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void processor() {
                authService.regist(request);
            }
        });
        return baseResult;
    }
}
