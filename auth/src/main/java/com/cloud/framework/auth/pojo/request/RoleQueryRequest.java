package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class RoleQueryRequest implements Serializable {
    /**
     * 角色名称
     */
    String roleName;

    /**
     * 状态
     *
     * @see BaseStatusEnum
     */
    String status;

    /**
     * 角色权限码
     */
    String roleCode;
}
