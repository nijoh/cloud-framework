package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.model.common.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 员工信息修改
 */
@Getter
@Setter
public class StaffInfoModifyRequest extends BaseRequest{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 438941815902918200L;

    /**
     * 员工ID
     */
    @NotNull(message = "未选择员工")
    private Integer staffId;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 员工性别
     * @see GenderEnum
     */
    @NotBlank(message = "未选择性别")
    private String gender;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

}
