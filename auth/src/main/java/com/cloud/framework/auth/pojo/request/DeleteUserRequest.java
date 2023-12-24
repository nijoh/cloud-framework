package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author nijo_h
 * * @date 2023/11/14 20:00
 */
@Getter
@Setter
public class DeleteUserRequest extends BaseRequest {
    /**
     * serialVersionUID
     * */
    private static final long serialVersionUID = -4057642581910978404L;

    /**
     * 登录电子邮箱
     * */
    @NotEmpty(message = "电子邮箱信息为空")
    private List<String> emailList;
}
