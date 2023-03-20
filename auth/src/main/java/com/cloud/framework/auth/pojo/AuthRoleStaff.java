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
 * 员工角色表
 * */
@Table(name = "auth_role_staff")
@Getter
@Setter
public class AuthRoleStaff implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 员工id
     */
    private Integer staffId;

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