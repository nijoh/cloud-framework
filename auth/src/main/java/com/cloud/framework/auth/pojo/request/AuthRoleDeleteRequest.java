package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRoleDeleteRequest extends BaseRequest {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5736415540913047087L;

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
}
