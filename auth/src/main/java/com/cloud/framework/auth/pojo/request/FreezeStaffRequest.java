package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.base.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 冻结用户请求
 */
@Getter
@Setter
public class FreezeStaffRequest extends BaseRequest {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6801934732991886280L;

    @NotBlank(message = "未选择用户")
    private String accountId;
}
