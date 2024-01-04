package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.base.BasePage;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询用户请求
 * @author nijo_h
 * * @date 2023/11/4 23:07
 */
@Getter
@Setter
public class QueryUserReuqest extends BasePage {
    /**
     * 员工名称
     * */
    private String staffName;

    /**
     * 创建开始时间
     * */
    private String startTime;

    /**
     * 创建结束时间
     * */
    private String endTime;

    /**
     * 账户状态
     */
    private String status;
}
