package com.cloud.framework.auth.service;

import com.cloud.framework.auth.utils.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractBaseService{
    //DB事务执行模版
    @Autowired
    protected TransactionService transactionService;

}
