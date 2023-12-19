package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统表
 *
 * */
@Table(name = "auth_ms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthMs implements Serializable {
    /**
     * 自增id
     */
    @GeneratedValue(generator = "JDBC")
    @Id
    private Integer id;

    /**
     * 系统名称
     */
    private String msName;

    /**
     * 系统描述
     */
    private String msDesc;

    /**
     * 系统域名
     */
    private String msDomain;

    /**
     * 创建时间
     */
    @CreateTime
    private LocalDateTime createTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 更新时间
     */
    @UpdateTime
    private LocalDateTime updateTime;


    /**
     * 状态 1:启用, 0:关闭, -1:删除标识
     * @see BaseStatusEnum
     */
    private Boolean status;
}