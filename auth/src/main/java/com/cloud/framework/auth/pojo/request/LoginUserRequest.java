package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录Request
 */
@Getter
@Setter
public class LoginUserRequest implements Serializable {

    private static final long serialVersionUID = -2756217208103103559L;

    @NotBlank(message = "电子邮箱不能为空")
    @Email(message = "登录邮箱不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String passWord;
}
