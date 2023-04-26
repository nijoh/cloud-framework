package com.cloud.framework.model.common.enums;

import lombok.Getter;

/**
 * 表状态status
 * @author nijo_h
 * * @date 2023/4/26 21:59
 */
@Getter
public enum BaseStatus {
    ENABLE(1,"开启"),
    DISABLE(0,"关闭"),
    DELETED(-1,"删除");
    private Integer code;
    private String desc;

    BaseStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
