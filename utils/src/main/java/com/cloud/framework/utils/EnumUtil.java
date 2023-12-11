package com.cloud.framework.utils;

import com.cloud.framework.model.common.base.CodeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * 枚举获取工具类
 * */
public class EnumUtil {
    public static <T extends CodeEnum> T getCode(Class<T> enumClass, String code) {
        for (T each : enumClass.getEnumConstants()) {
            if(StringUtils.equals(each.getCode(),code)){
                return each;
            }
        }
        return null;
    }
}
