package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class QueryAuthorizeRequest implements Serializable {
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
}
