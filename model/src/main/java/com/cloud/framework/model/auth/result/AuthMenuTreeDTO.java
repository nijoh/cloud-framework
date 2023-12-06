package com.cloud.framework.model.auth.result;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AuthMenuTreeDTO extends AuthMenuDTO{
    /**
     * 子节点
     */
    private List<AuthMenuTreeDTO> children =new ArrayList<>();
}
