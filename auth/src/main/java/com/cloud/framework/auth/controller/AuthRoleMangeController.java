package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.AuthRole;
import com.cloud.framework.auth.pojo.request.AuthRoleCreateRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleDeleteRequest;
import com.cloud.framework.auth.pojo.request.AuthRoleModifyRequest;
import com.cloud.framework.auth.pojo.request.AuthorizeMenusRequest;
import com.cloud.framework.auth.service.AuthRoleService;
import com.cloud.framework.model.auth.result.AddRoleDTO;
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
     *
     * @param request 请求参数
     * @return
     */
    @PostMapping("/add")
    public BaseResult<AddRoleDTO> addRole(@RequestBody @Validated AuthRoleCreateRequest request) {
        BaseResult<AddRoleDTO> baseResult = new BaseResult();
        AddRoleDTO addRoleDTO=new AddRoleDTO();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void checkParam() {
                AssertUtil.inEnum(request.getStatus(), BaseStatusEnum.class, "角色状态错误");
            }

            @Override
            public void processor() {
                AuthRole authRole = authRoleService.addRole(request);
                addRoleDTO.setRoleId(authRole.getId());
                baseResult.setContent(addRoleDTO);
            }
        });
        return baseResult;
    }

    /**
     * 修改角色
     *
     * @param request
     * @return
     */
    @PostMapping("/modify")
    public BaseResult modifyRole(@RequestBody @Validated AuthRoleModifyRequest request) {
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void checkParam() {
                AssertUtil.inEnum(request.getStatus(), BaseStatusEnum.class, "角色状态错误");
            }

            @Override
            public void processor() {
                authRoleService.modifyRole(request);
            }
        });
        return baseResult;
    }

    /**
     * 删除角色
     *
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResult deleteRole(@RequestBody @Validated AuthRoleDeleteRequest request) {
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void processor() {
                authRoleService.deleteRole(request);
            }
        });
        return baseResult;
    }

    /**
     * 授权角色菜单
     *
     * @param request
     * @return
     */
    @PostMapping("/authorizeMenus")
    public BaseResult authorizeMenus(@RequestBody @Validated AuthorizeMenusRequest request) {
        BaseResult baseResult = new BaseResult();
        ApiProcessor.processor(baseResult, new BusinessTemplate() {
            @Override
            public void processor() {
                authRoleService.authorizeMenus(request);
            }
        });
        return baseResult;
    }
}
