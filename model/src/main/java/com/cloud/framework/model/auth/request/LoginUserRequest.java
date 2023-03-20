package com.cloud.framework.model.auth.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录Request
 */
@Getter
@Setter
public class LoginUserRequest {
    private String email;

    private String passWord;
}
