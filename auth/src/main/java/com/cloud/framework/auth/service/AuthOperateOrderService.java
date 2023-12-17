package com.cloud.framework.auth.service;

/**
 * Auth操作流水Service
 * */
public interface AuthOperateOrderService {
    /**
     * 创建操作流水
     * @param bizNo
     * @param extendInfo
     * @param operateType
     */
    void createOperateOrder(String bizNo,String extendInfo,String operateType);

    /**
     * 创建操作流水
     * @param bizNo
     * @param operateType
     */
    void createOperateOrder(String bizNo,String operateType);
}
