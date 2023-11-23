package com.cloud.framework.auth.dal;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.StaffInfo;


/**
 * 员工信息Mapper
 */
@Mapper
public interface StaffInfoMapper extends MyBaseMapper<StaffInfo> {
    void deleteListStaffInfoByUserId(List<String> userIdList);
}