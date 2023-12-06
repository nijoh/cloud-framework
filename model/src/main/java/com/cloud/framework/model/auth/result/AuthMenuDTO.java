package com.cloud.framework.model.auth.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 菜单信息
 * @author nijo_h
 * * @date 2023/11/20 22:34
 */
@Getter
@Setter
public class AuthMenuDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7647403173859963361L;

    /**
     * 菜单id
     * */
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
     * 菜单码
     * */
    private String menuCode;

    /**
     * 菜单类型
     */
    private String menuType;
}
