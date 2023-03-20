package com.cloud.framework.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * DB事务执行模版
 * */
@Component
public class TransactionService<T> {
    @Autowired
    private TransactionTemplate transactionTemplate;


    public void processor(TransactionProcessor<T> transactionProcessor){
        try {
            transactionTemplate.execute(status -> {
                transactionProcessor.checkBiz();
                transactionProcessor.assembly();
                transactionProcessor.checkIdempotent();
                transactionProcessor.saveOrder();
                transactionProcessor.processor();
                return transactionProcessor;
            });
        } catch (DuplicateKeyException e) {
            //插入数据库失败 可能是保存流水唯一索引冲突
            e.printStackTrace();
        }
    }
}
