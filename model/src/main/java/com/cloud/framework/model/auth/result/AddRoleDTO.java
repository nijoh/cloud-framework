package com.cloud.framework.model.auth.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 新增角色返回
 */
@Getter
@Setter
public class AddRoleDTO implements Serializable {
    private static final long serialVersionUID = -1801481857635973387L;

    /**
     * 角色ID
     */
    private Integer roleId;
}
