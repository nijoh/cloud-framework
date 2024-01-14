package com.cloud.framework.auth.controller;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.auth.pojo.request.QueryAuthorizeRequest;
import com.cloud.framework.auth.pojo.request.RoleQueryRequest;
import com.cloud.framework.auth.service.AuthRoleQueryService;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthRoleDTO;
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

import java.util.List;

@RestController
@RequestMapping("/roles")
public class AuthRoleQueryController {
    @Autowired
    private AuthRoleQueryService roleQueryService;

    /**
     * 查询系统角色
     *
     * @param request
     * @return
     */
    @PostMapping("/query")
    public BaseResult<List<AuthRoleDTO>> queryRole(@RequestBody RoleQueryRequest request) {
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void checkParam() {
                if (StringUtils.isNotBlank(request.getStatus())) {
                    AssertUtil.inEnum(request.getStatus(), BaseStatusEnum.class, "角色状态错误");
                }
            }

            @Override
            public void processor() {
                baseResult.setContent(roleQueryService.queryRole(request));
            }
        });
        return baseResult;
    }

    /**
     * 查询系统角色
     *
     * @param request
     * @return
     */
    @PostMapping("/queryAuthorize")
    public BaseResult<List<AuthMenuDTO>> queryAuthorize(@RequestBody @Validated QueryAuthorizeRequest request) {
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {

            @Override
            public void processor() {
                baseResult.setContent(roleQueryService.queryAuthorize(request.getRoleId()));
            }
        });
        return baseResult;
    }
}
