package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色请求
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthRoleBaseRequest implements Serializable {
    private static final long serialVersionUID = 2039861536587161187L;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 状态 1:启用, 0:关闭, -1:删除标识
     * @see BaseStatusEnum
     */
    @NotBlank(message = "角色状态不能为空")
    private String status;

}
