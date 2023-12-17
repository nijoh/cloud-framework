package com.cloud.framework.auth.pojo.request;

import com.cloud.framework.auth.pojo.enums.MenuTypeEnum;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthMenuBaseRequest extends BaseRequest{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4405802743549742407L;
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 菜单描述
     */
    private String menuDesc;

    /**
     * 菜单uri
     */
    @NotBlank(message = "菜单URL不能为空")
    private String menuUrl;

    /**
     * 状态
     * @see BaseStatusEnum
     */
    @NotBlank(message = "菜单状态不能为空")
    private String status;

    /**
     * 父菜单id
     */
    @NotNull(message = "父级菜单不能为空")
    private Integer parentId;

    /**
     * 菜单类型
     * @see MenuTypeEnum
     */
    @NotBlank(message = "菜单类型不能为空")
    private String menuType;

    /**
     * 菜单码
     * */
    @NotBlank(message = "菜单标识码不能为空")
    private String menuCode;
}
