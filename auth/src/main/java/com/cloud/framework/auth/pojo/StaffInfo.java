package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import com.cloud.framework.model.common.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 员工信息表
 */
@Table(name = "staff_info")
@Getter
@Setter
public class StaffInfo implements Serializable {
    /**
     * 员工id
     */
    @GeneratedValue(generator = "JDBC")
    @Id
    private Integer id;

    /**
     * 账号id
     */
    private String uid;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工昵称
     */
    private String nickname;

    /**
     * 员工头像
     */
    private String staffImage;

    /**
     * 员工性别
     * @see GenderEnum
     */
    private String gender;

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

    /**
     * 操作人
     */
    private String operator;

    /**
     * 系统域名
     */
    private String msDomain;
}