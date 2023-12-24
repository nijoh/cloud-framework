package com.cloud.framework.auth.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.atomic.AtomicReference;

/**
 * DB事务执行模版
 */
@Component
public class TransactionService {
    @Autowired
    private TransactionTemplate transactionTemplate;


    public <T> T processor(TransactionProcessor<T> transactionProcessor) {
        AtomicReference<T> model = new AtomicReference<>();
        try {
            transactionTemplate.execute(status -> {
                model.set(transactionProcessor.checkBiz());
                //枷锁-记录操作流水
                transactionProcessor.saveOrder(model.get());
                //组装数据
                transactionProcessor.assembly(model.get());
                //处理数据
                transactionProcessor.processor(model.get());
                return transactionProcessor;
            });
        } catch (DuplicateKeyException e) {
            //幂等插入数据库失败
            transactionProcessor.checkIdempotent(model.get());
        }
        return model.get();
    }

}
