package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 账户信息表
 * */
@Table(name = "account_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountUser implements Serializable {
    /**
     * 账号id
     */
    @Id
    private String id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 最后一次登陆时间
     */
    @UpdateTime
    private LocalDateTime lastLoginTime;

    /**
     * 最后一次登陆ip
     */
    private String loginIp;

    /**
     * @see BaseStatusEnum
     */
    private String status;

    /**
     * 操作人
     */
    private String operate;

    /**
     * 系统域名
     */
    private String msDomain;
}