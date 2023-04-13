package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AuthService;
import com.cloud.framework.model.common.ApiProcessor;
import com.cloud.framework.model.common.BusinessTemplate;
import com.cloud.framework.model.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<String> login(@RequestBody @Validated LoginUserRequest request){
        Result<String> result=new Result<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                String token = authService.login(request);
                result.setData(token);
            }
        });
        return result;
    }

    /**
     * 用户注册
     * */
    @PostMapping("/regist")
    public Result regist(@RequestBody @Validated RegistAccountUserRequest request){
        Result result=new Result<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                authService.regist(request);
            }
        });
        return result;
    }
}
