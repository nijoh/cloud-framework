package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.MyBaseMapper;
import com.cloud.framework.auth.pojo.StaffInfo;
import org.apache.ibatis.annotations.Mapper;


/**
 * 员工信息Mapper
 */
@Mapper
public interface StaffInfoMapper extends MyBaseMapper<StaffInfo> {

}