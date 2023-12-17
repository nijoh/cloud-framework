package com.cloud.framework.auth.utils.convert;

import com.cloud.framework.auth.pojo.AuthOperateOrder;

public class AuthOperateOrderConvert {
    /**
     * 构造DO数据
     * @param bizNo
     * @param extendInfo
     * @param operateType
     * @return
     */
    public static AuthOperateOrder buildModelDO(String bizNo,String extendInfo,String operateType){
        AuthOperateOrder authOperateOrder=new AuthOperateOrder();
        authOperateOrder.setBizNo(bizNo);
        authOperateOrder.setExtendInfo(extendInfo);
        authOperateOrder.setOperateType(operateType);
        return authOperateOrder;
    }

    /**
     * 构造DO数据
     * @param bizNo
     * @param operateType
     * @return
     */
    public static AuthOperateOrder buildModelDO(String bizNo,String operateType){
        AuthOperateOrder authOperateOrder=new AuthOperateOrder();
        authOperateOrder.setBizNo(bizNo);
        authOperateOrder.setOperateType(operateType);
        return authOperateOrder;
    }
}
