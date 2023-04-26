package com.cloud.framework.model.common.base;

/**
 * 业务模版执行通用方法
 * */
public interface BusinessTemplate {
    /**
     * 参数校验
     * */
    default void checkParam() {

    }

    /**
     * 处理逻辑
     * */
    void processor();
}
