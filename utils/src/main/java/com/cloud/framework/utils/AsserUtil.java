package com.cloud.framework.utils;

import com.cloud.framework.utils.exceptions.AsserException;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言工具类
 */
public class AsserUtil {
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
     * @param obj   断言类
     * @param model 模块
     * @param msg   异常信息
     */
    public static void notNull(Object obj, String model, String msg) {
        if (!Objects.nonNull(obj)) {
            throw new AsserException(msg);
        }
    }

    /**
     * 断言必须为NULL或者空内容,否则异常
     *
     * @param collection 集合
     * @param model      模块
     * @param msg        异常信息
     */
    public static void isEmpty(Collection collection, String model, String msg) {
        if (!Objects.isNull(collection) || !collection.isEmpty()) {
            throw new AsserException(msg);

        }
    }

    /**
     * 断言必须有内容非NULL,否则异常
     *
     * @param collection 集合
     * @param model      模块
     * @param msg        异常信息
     */
    public static void notEmpty(Collection collection, String model, String msg) {
        if (!Objects.nonNull(collection) || !collection.isEmpty()) {
            throw new AsserException(msg);
        }
    }

    /**
     * 断言必须True,否则异常
     *
     * @param obj   断言内容
     * @param model 模块
     * @param msg   异常信息
     */
    public static void isTrue(Boolean obj, String model, String msg) {
        if (!obj) {
            throw new AsserException(msg);
        }
    }
}
