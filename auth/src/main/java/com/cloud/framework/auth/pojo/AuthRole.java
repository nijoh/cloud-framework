package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统角色表
 */
@Table(name = "auth_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRole implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer id;

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
    @CreateTime
    private LocalDateTime createTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 更新时间
     */
    @UpdateTime
    private LocalDateTime updateTime;

    /**
     * 状态 1:启用, 0:关闭, -1:删除标识
     *
     * @see BaseStatusEnum
     */
    private String status;


    /**
     * 系统域名
     */
    private String msDomain;
}