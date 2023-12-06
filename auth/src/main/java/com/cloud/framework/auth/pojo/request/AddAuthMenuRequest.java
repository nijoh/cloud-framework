package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 新增菜单Request
 * @author nijo_h
 * * @date 2023/3/30 11:31 下午
 */
@Getter
@Setter
public class AddAuthMenuRequest implements Serializable {
    private static final long serialVersionUID = -3032639022045169143L;

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
     * 状态 1:enable, 0:disable, -1:deleted
     */
    private Boolean status;

    /**
     * 父菜单id
     */
    private Integer parentId;

    /**
     * 菜单类型
     */
    private String menuType;
}
