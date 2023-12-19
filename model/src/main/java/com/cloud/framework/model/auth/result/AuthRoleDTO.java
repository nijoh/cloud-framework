package com.cloud.framework.model.auth.result;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuthRoleDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3429846880256559365L;


    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 状态
     *
     * @see BaseStatusEnum
     */
    private String status;


    /**
     * 角色ID
     * */
    private Integer roleId;

}
