package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class BaseRequest implements Serializable {
    /**
     * serialVersionUID
     * */
    private static final long serialVersionUID = 1299698770638379366L;

    /**
     * 业务号
     */
    @NotBlank(message = "业务号不能为空")
    private String bizNo;
}
