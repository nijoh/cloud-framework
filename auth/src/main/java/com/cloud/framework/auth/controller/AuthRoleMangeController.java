package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.AuthRoleAddRequest;
import com.cloud.framework.auth.service.AuthRoleService;
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

@RestController
@RequestMapping("/roles")
public class AuthRoleMangeController {

    /**
     * 系统角色Service
     */
    @Autowired
    private AuthRoleService authRoleService;

    /**
     * 新增角色
     * @param request 请求参数
     * @return
     */
    @PostMapping("/add")
    public BaseResult addMenu(@RequestBody @Validated AuthRoleAddRequest request){
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void checkParam() {
                AssertUtil.inEnum(request.getStatus(), BaseStatusEnum.class,"角色状态错误");
            }

            @Override
            public void processor() {
                authRoleService.addRole(request);
            }
        });
        return baseResult;
    }
}
