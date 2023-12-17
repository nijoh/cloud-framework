package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统权限表
 */
@Table(name = "auth_role_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRoleMenu implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2117990316785288349L;
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    @Id
    private Integer id;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 角色
     */
    private Integer roleId;

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
     * 状态
     *
     * @see BaseStatusEnum
     */
    private String status;

    /**
     * 系统域名
     */
    private String msDomain;
}