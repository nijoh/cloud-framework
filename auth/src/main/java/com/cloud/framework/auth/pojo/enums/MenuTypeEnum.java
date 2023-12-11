package com.cloud.framework.auth.pojo.enums;

import com.cloud.framework.model.common.base.CodeEnum;

/**
 * 菜单类型枚举
 */
public enum MenuTypeEnum implements CodeEnum {
    MENU_NAVIGATION("navigation","菜单导航"),
    MENU_RESOURCE("resource","菜单资源");

    MenuTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String code;
    private String desc;
}
