package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.CreateTime;
import com.cloud.framework.model.common.UpdateTime;
import lombok.Getter;
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
public class AuthRole implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String desc;

    /**
     * 创建时间
     */
    @CreateTime
    private LocalDateTime createAt;

    /**
     * 创建人staff_id
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    @UpdateTime
    private LocalDateTime updateAt;

    /**
     * 修改人staff_id
     */
    private Integer updateBy;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    private Boolean status;
}