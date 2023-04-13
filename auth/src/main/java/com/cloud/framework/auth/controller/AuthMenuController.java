package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.AddAuthMenuRequest;
import com.cloud.framework.auth.service.AuthMenuService;
import com.cloud.framework.model.common.ApiProcessor;
import com.cloud.framework.model.common.BusinessTemplate;
import com.cloud.framework.model.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理
 * @author nijo_h
 * * @date 2023/3/30 11:27 下午
 */
@RestController
@RequestMapping("/authMenu")
public class AuthMenuController {
    @Autowired
    private AuthMenuService authMenuService;
    /**
     * 新增菜单
     * */
    @PostMapping("/add")
    public Result addMenu(@RequestBody AddAuthMenuRequest request){
        Result result = new Result();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                authMenuService.addMenu(request);
            }
        });
        return result;
    }
}
