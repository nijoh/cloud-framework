package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class AuthorizeMenusRequest extends BaseRequest {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 704498535677973115L;

    /**
     * 角色ID
     */
    @NotNull(message = "授权角色不能为空")
    private Integer roleId;

    /**
     * 授权菜单
     */
    @NotEmpty(message = "授权菜单不能为空")
    private List<Integer> menuIds;
}
