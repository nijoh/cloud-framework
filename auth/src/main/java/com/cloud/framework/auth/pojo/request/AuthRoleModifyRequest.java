package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer roleId;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2957545090262180510L;
}
