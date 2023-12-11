package com.cloud.framework.model.common.enums;

import com.cloud.framework.model.common.base.CodeEnum;
import lombok.Getter;

/**
 * 表状态status
 * @author nijo_h
 * * @date 2023/4/26 21:59
 */
@Getter
public enum BaseStatusEnum implements CodeEnum {
    NORMAL("NORMAL","正常"),
    FREEZE("FREEZE","冻结"),
    DELETED("DELETED","删除");
    private String code;
    private String desc;

    BaseStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
