package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统菜单查询请求
 */
@Getter
@Setter
public class AuthMenuQueryRequest implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5083938998210282832L;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型
     * @see com.cloud.framework.auth.pojo.enums.MenuTypeEnum
     */
    private String menuType;

    /**
     * 状态
     *
     * @see BaseStatusEnum
     */
    String status;
}
