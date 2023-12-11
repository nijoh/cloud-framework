package com.cloud.framework.model.auth.result;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账户信息
 * @author nijo_h
 * * @date 2023/11/2 22:17
 */
@Getter
@Setter
public class AccountUserDTO implements Serializable {
    /**
     * serialVersionUID
     * */
    private static final long serialVersionUID = 3762660608027544721L;

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
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 最后一次登陆时间
     */
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
     * 系统域名
     */
    private String msDomain;
}
