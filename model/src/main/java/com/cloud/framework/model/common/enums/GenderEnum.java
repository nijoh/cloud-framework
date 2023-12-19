package com.cloud.framework.model.common.enums;

import com.cloud.framework.model.common.base.CodeEnum;
import lombok.Getter;

/**
 * 性别枚举
 */
@Getter
public enum GenderEnum implements CodeEnum {
    MAN("man","男"),
    WOMAN("woman","女");

    private String code;
    private String desc;

     GenderEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return null;
    }
}
