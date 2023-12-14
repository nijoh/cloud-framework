package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 角色修改请求
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthRoleModifyRequest extends AuthRoleBaseRequest{

    /**
     * 角色ID
     * */
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2957545090262180510L;
}
