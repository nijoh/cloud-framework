package com.cloud.framework.auth.dal;

import com.cloud.framework.auth.config.mybatis.MyBaseMapper;
import com.cloud.framework.auth.pojo.StaffInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 员工信息Mapper
 */
@Mapper
public interface StaffInfoMapper extends MyBaseMapper<StaffInfo> {
    void deleteListStaffInfoByUserId(List<String> userIdList);
}