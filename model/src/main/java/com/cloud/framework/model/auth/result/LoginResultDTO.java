package com.cloud.framework.model.auth.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录结果返回
 * 
 * @author nijo_h * @date 2023/11/11 12:16
 */
@Getter
@Setter
public class LoginResultDTO extends UserInfoDetailDTO {
    /**
     * serialVersionUID
     * */
    private static final long serialVersionUID = 6539545398596014422L;

    // 授权Token
    private String AuthorizationToken;
}
