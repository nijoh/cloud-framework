package com.cloud.framework.model.common.enums;

import lombok.Getter;

/**
 * 表状态status
 * @author nijo_h
 * * @date 2023/4/26 21:59
 */
@Getter
public enum BaseStatus {
    NORMAL("NORMAL","正常"),
    FREEZE("FREEZE","冻结"),
    DELETED("DELETED","删除");
    private String code;
    private String desc;

    BaseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
