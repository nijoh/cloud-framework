package com.cloud.framework.model.auth.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 保存账户信息请求
 * */
@Getter
@Setter
public class SaveAccountUserRequest implements Serializable {
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
}
