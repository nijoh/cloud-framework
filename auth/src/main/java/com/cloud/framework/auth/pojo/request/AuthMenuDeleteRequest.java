package com.cloud.framework.auth.pojo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单删除
 */
@Getter
@Setter
public class AuthMenuDeleteRequest implements Serializable {
    /**
     * 菜单ID
     */
    @NotEmpty(message = "菜单ID不能为空")
    private List<Integer> menuId;
}
