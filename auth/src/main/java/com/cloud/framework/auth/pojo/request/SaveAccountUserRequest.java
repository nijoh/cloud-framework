package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "电子邮箱不能为空")
    @Email(message = "登录邮箱不正确")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能不为空")
    private String password;
}
