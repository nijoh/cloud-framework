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
 * 系统菜单表
 *
 */
@Table(name ="auth_ms_menu")
@Getter
@Setter
public class AuthMsMenu implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 系统id
     */
    private Integer msId;

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
    private String menuUri;

    /**
     * 创建时间
     */
    @CreateTime
    private LocalDateTime createAt;

    /**
     * 是否展示菜单
     */
    private String isShow;

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