package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "auth_operate_order")
public class AuthOperateOrder {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer orderId;

    /**
     * 业务订单号
     */
    private String bizNo;

    /**
     * 扩展信息
     */
    private String extendInfo;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 系统所属域
     */
    private String msDomain;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    @CreateTime
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @UpdateTime
    private LocalDateTime updateTime;
}
