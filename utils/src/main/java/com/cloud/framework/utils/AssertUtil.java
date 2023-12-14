package com.cloud.framework.utils;

import com.cloud.framework.model.common.base.CodeEnum;
import com.cloud.framework.utils.exceptions.AsserException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言工具类
 */
public class AssertUtil {

    /**
     * 断言比较两个值，必须true
     *
     * @param param  参数
     * @param params 参数2
     * @param msg    异常信息
     */
    public static void equals(String param, String params, String msg, Object... args) {
        if (!StringUtils.equals(param, params)) {
            throw new AsserException(msg);
        }
    }

    /**
     * 断言必须为NULL,否则异常
     *
     * @param obj   断言类
     * @param model 模块
     * @param msg   异常信息
     */
    public static void isNull(Object obj, String model, String msg) {
        if (!Objects.isNull(obj)) {
            throw new AsserException(msg);
        }
    }

    /**
     * 断言必须为非空,否则异常
     *
     * @param obj 断言类
     * @param msg 异常信息
     */
    public static void notNull(Object obj, String msg, Object... args) {
        if (!Objects.nonNull(obj)) {
            throw new AsserException(String.format(msg, args));
        }
    }

    /**
     * 断言必须为NULL或者空内容,否则异常
     *
     * @param collection 集合
     * @param msg        异常信息
     */
    public static void isEmpty(Collection collection, String msg, Object... value) {
        if (Objects.nonNull(collection) && !collection.isEmpty()) {
            throw new AsserException(String.format(msg, value));

        }
    }

    /**
     * 断言必须有内容非NULL,否则异常
     *
     * @param collection 集合
     * @param msg        异常信息
     */
    public static void notEmpty(Collection collection, String msg, Object... value) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new AsserException(String.format(msg, value));
        }
    }

    /**
     * 断言必须True,否则异常
     *
     * @param obj 断言内容
     * @param msg 异常信息
     */
    public static void isTrue(Boolean obj, String msg, Object... value) {
        if (!obj) {
            throw new AsserException(String.format(msg, value));
        }
    }

    /**
     * 断言必须False,否则异常
     *
     * @param obj 断言内容
     * @param msg 异常信息
     */
    public static void isFalse(Boolean obj, String msg, Object... value) {
        if (obj) {
            throw new AsserException(String.format(msg, value));
        }
    }

    /**
     * 判断code是否在枚举中
     *
     * @param code
     * @param enumClass
     * @param msg
     * @param args
     */
    public static void inEnum(String code, Class<? extends CodeEnum> enumClass, String msg, Object... args) {
        notNull(EnumUtil.getCode(enumClass, code), msg, args);
    }
}
