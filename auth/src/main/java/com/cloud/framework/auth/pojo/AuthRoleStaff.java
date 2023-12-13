package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 员工角色表
 */
@Table(name = "auth_role_staff")
@Getter
@Setter
public class AuthRoleStaff implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    @Id
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 员工id
     */
    private Integer staffId;

    /**
     * 创建时间
     */
    @CreateTime
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @UpdateTime
    private LocalDateTime updateTime;

//    /**
//     * 状态
//     * @see BaseStatusEnum
//     */
//    private String status;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 系统域名
     */
    private String msDomain;
}