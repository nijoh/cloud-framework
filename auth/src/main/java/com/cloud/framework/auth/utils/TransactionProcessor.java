package com.cloud.framework.auth.utils;

/**
 * DB事务模版
 * */
public interface TransactionProcessor<T>{
    //前置检查
    default void checkBiz(){};

    //组装参数
    default void assembly(){};

    //幂等校验
    default void checkIdempotent(){};

    //落操作流水
    default void saveOrder(){}

    //处理
    void processor();
}
