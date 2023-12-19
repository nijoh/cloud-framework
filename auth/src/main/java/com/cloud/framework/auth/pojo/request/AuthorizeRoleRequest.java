package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthorizeRoleRequest extends BaseRequest{
    /**
     * serialVersionUID
     * */
    private static final long serialVersionUID = -1063873425670013287L;


    /**
     * 角色ID
     */
    @NotNull(message = "未选择角色")
    private Integer roleId;

    /**
     * 员工ID
     */
    @NotNull(message = "未选择员工")
    private Integer staffId;
}
