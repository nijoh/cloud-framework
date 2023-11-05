package com.cloud.framework.auth.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper类
 * */
public interface MyBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
