package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthOperateOrderMapper;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthOperateOrderService;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cloud.framework.auth.utils.convert.AuthOperateOrderConvert.buildModelDO;

/**
 * 订单流水操作实现类
 */
@Service
public class AuthOperateOrderServiceImpl extends AbstractBaseService implements AuthOperateOrderService {
    @Autowired
    private AuthOperateOrderMapper operateOrderMapper;

    /**
     * @see AuthOperateOrderService#createOperateOrder(String, String, String)
     */
    @Override
    public void createOperateOrder(String bizNo, String extendInfo, String operateType) {
        int result = operateOrderMapper.insert(buildModelDO(bizNo, extendInfo, operateType));
        AssertUtil.isTrue(result > 0, CloudConstant.DB_INSERT_ERROR);
    }

    @Override
    public void createOperateOrder(String bizNo, String operateType) {
        int result = operateOrderMapper.insert(buildModelDO(bizNo,operateType));
        AssertUtil.isTrue(result > 0, CloudConstant.DB_INSERT_ERROR);
    }
}
