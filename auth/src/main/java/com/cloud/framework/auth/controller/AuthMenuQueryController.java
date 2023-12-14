package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.AuthMenuQueryRequest;
import com.cloud.framework.auth.service.AuthMenuQueryService;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthMenuTreeDTO;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/query")
    public BaseResult<List<AuthMenuDTO>> queryMenu(@RequestBody AuthMenuQueryRequest request){
        BaseResult<List<AuthMenuDTO>> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setContent(authMenuQueryService.queryMenu(request));
            }
        });
        return result;
    }

    @GetMapping("/queryTree")
    public BaseResult<List<AuthMenuTreeDTO>> queryTree(){
        BaseResult<List<AuthMenuTreeDTO>> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setContent(authMenuQueryService.queryTree());
            }
        });
        return result;

    }
}
