package com.cloud.framework.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 默认构造器
 *
 * @author nijo_h
 * * @date 2023/11/11 10:53
 */
public class DefaultUtil {
    /**
     * 默认可编辑List
     *
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> defaultEditArrayList(List<T> params) {
        if (Objects.isNull(params) || params.isEmpty()) {
            return new ArrayList<>();
        }
        return params;
    }


    /**
     * 获取默认值
     * @param value
     * @param defaultValue
     * @return
     */
    public static String defaultValue(final String value,String defaultValue){
        if(StringUtils.isNotBlank(value)){
            return value;
        }
        return defaultValue;
    }
}
