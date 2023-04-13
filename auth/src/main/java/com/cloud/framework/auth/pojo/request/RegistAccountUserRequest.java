package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 保存账户信息请求
 * */
@Getter
@Setter
public class RegistAccountUserRequest implements Serializable {

    private static final long serialVersionUID = 4628135663377598437L;

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

    /**
     * 真实姓名（员工姓名）
     * */
    @NotBlank(message = "真实姓名不能为空")
    private String name;

    /**
     * 性别 1:男 2:女
     * */
    @NotNull(message = "性别不能为空")
    private Character gender;
}
