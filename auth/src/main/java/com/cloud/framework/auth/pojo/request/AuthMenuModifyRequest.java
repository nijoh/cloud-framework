package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthMenuModifyRequest extends AuthMenuBaseRequest {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4745900234913940250L;

    /**
     * 菜单ID
     */
    @NotNull(message = "菜单ID不能为空")
    private Integer menuId;
}
