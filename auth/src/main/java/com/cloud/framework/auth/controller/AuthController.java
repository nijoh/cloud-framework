package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.service.AuthService;
import com.cloud.framework.model.auth.request.LoginUserRequest;
import com.cloud.framework.model.common.ApiProcessor;
import com.cloud.framework.model.common.BusinessTemplate;
import com.cloud.framework.model.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AuthService authService;

    /**
     * 登录方法
     * @param request 请求参数
     * @return Result<String> 返回结果
     * */
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginUserRequest request){
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
}
