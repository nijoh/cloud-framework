package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.AuthOperateOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthOperateOrderMapper extends MyBaseMapper<AuthOperateOrder> {
}
