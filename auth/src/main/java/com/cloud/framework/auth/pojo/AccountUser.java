package com.cloud.framework.auth.pojo;

import com.cloud.framework.model.common.CreateTime;
import com.cloud.framework.model.common.UpdateTime;
import lombok.Getter;
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
    private LocalDateTime createAt;

    /**
     * 创建ip
     */
    private String createIpAt;

    /**
     * 最后一次登陆时间
     */
    @UpdateTime
    private LocalDateTime lastLoginAt;

    /**
     * 最后一次登陆ip
     */
    private String lastLoginIpAt;

    /**
     * 登录次数
     */
    private Integer loginTimes;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    private Boolean status;
}