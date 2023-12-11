package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.enums.MenuTypeEnum;
import com.cloud.framework.auth.pojo.request.AuthMenuAddRequest;
import com.cloud.framework.auth.service.AuthMenuService;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import com.cloud.framework.model.common.result.BaseResult;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/meuns")
public class AuthMenuMangerController {
    @Autowired
    private AuthMenuService authMenuService;

    /**
     * 新增菜单
     * */
    @PostMapping("/add")
    public BaseResult addMenu(@RequestBody @Validated AuthMenuAddRequest request){
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void checkParam() {
                AssertUtil.inEnum(request.getStatus(), BaseStatusEnum.class,"菜单状态错误");
                AssertUtil.inEnum(request.getMenuType(), MenuTypeEnum.class,"菜单类型错误");
            }

            @Override
            public void processor() {
                authMenuService.addMenu(request);
            }
        });
        return baseResult;
    }
}
