package com.cloud.framework.auth.utils.convert;

import com.cloud.framework.auth.pojo.AuthRole;
import com.cloud.framework.model.auth.result.AuthRoleDTO;
import com.cloud.framework.utils.DefaultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统角色转换器
 */
public class AuthRoleConvert {
    /**
     * 模型List转换DTO List
     *
     * @param authRoleList
     * @return
     */
    public static List<AuthRoleDTO> converToList(List<AuthRole> authRoleList) {
        List<AuthRoleDTO> result = new ArrayList<>();
        DefaultUtil.defaultEditArrayList(authRoleList).stream().forEach(authRole -> {
            result.add(converModelToDTO(authRole));
        });
        return result;
    }

    /**
     * 模型转DTO
     *
     * @param authRole
     * @return
     */
    public static AuthRoleDTO converModelToDTO(AuthRole authRole) {
        AuthRoleDTO authRoleDTO = new AuthRoleDTO();
        authRoleDTO.setRoleName(authRole.getRoleName());
        authRoleDTO.setRoleDesc(authRole.getRoleDesc());
        authRoleDTO.setCreateTime(authRole.getCreateTime());
        authRoleDTO.setOperator(authRole.getOperator());
        authRoleDTO.setStatus(authRole.getStatus());
        return authRoleDTO;
    }
}
