package com.cloud.framework.auth.utils;

/**
 * DB事务模版
 * */
public interface TransactionProcessor<T>{

    //前置检查
    default void checkBiz(){};

    //加锁-落操作流水
    default T saveOrder(){return null;}

    //组装参数
    default void assembly(T t){};

    //幂等校验
    default void checkIdempotent(T t){};

    //处理
    void processor(T t);
}
