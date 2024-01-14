package com.cloud.framework.auth.pojo;

import com.cloud.framework.auth.pojo.enums.MenuTypeEnum;
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
 * 系统菜单表
 */
@Table(name = "auth_ms_menu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthMsMenu implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    @Id
    private Integer id;

    /**
     * 系统域名
     */
    private String msDomain;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单描述
     */
    private String menuDesc;

    /**
     * 菜单uri
     */
    private String menuUrl;

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
     * 菜单码
     */
    private String menuCode;

    /**
     * 菜单类型
     *
     * @see MenuTypeEnum
     */
    private String menuType;

    /**
     * 授权码（鉴权）
     */
    private String authorizeCode;
}