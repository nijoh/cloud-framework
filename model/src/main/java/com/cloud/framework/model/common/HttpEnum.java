package com.cloud.framework.model.common;

/**
 * 异常枚举定义
 * */
public enum HttpEnum {
    SUCCESS(200,"成功"),
    UNAUTHORIZED(401, "登录凭证过期!"),
    FORBIDDEN(403, "没有访问权限!"),
    UNKNOWN(1000, "未知异常!"),
    BUSINESS(1001,"业务异常");

    private Integer code;
    private String desc;

    HttpEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
