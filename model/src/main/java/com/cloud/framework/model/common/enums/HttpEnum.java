package com.cloud.framework.model.common.enums;

import lombok.Getter;

/**
 * 异常枚举定义
 * */
@Getter
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
}
