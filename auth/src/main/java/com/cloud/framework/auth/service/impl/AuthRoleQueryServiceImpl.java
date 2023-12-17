package com.cloud.framework.auth.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.auth.dal.AuthRoleMapper;
import com.cloud.framework.auth.dal.AuthRoleMenuMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.AuthRole;
import com.cloud.framework.auth.pojo.request.RoleQueryRequest;
import com.cloud.framework.auth.service.AuthRoleQueryService;
import com.cloud.framework.auth.utils.convert.AuthMenuConvert;
import com.cloud.framework.auth.utils.convert.AuthRoleConvert;
import com.cloud.framework.model.auth.result.AuthMenuDTO;
import com.cloud.framework.model.auth.result.AuthRoleDTO;
import com.cloud.framework.utils.CloudStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

/**
 * 角色查询ServiceImpl
 */
@Service
public class AuthRoleQueryServiceImpl implements AuthRoleQueryService {
    @Autowired
    private AuthRoleMapper roleMapper;

    @Autowired
    private AuthRoleMenuMapper roleMenuMapper;

    /**
     * @see AuthRoleQueryService#queryRole(RoleQueryRequest)
     */
    @Override
    public List<AuthRoleDTO> queryRole(RoleQueryRequest request) {
        List<AuthRole> authRoleList = roleMapper.selectByExample(queryRoleExample(request));
        return AuthRoleConvert.converToList(authRoleList);
    }

    /**
     * @see AuthRoleQueryService#queryAuthorize(Integer)
     */
    @Override
    public List<AuthMenuDTO> queryAuthorize(Integer roleId) {
        List<AuthMsMenu> authMsMenus = roleMenuMapper.queryAuthorize(roleId);
        return AuthMenuConvert.converToDTOFromList(authMsMenus);
    }


    /**
     * 查询系统用户角色 条件构造器
     *
     * @param
     * @return
     */
    public Example queryRoleExample(RoleQueryRequest request) {
        WeekendSqls<AuthRole> weekendSqls = WeekendSqls.<AuthRole>custom();
        if (StringUtils.isNotBlank(request.getRoleName())) {
            weekendSqls.andLike(AuthRole::getRoleName, CloudStringUtils.GeneratLikeString(request.getRoleName()));
        }
        if (StringUtils.isNotBlank(request.getStatus())) {
            weekendSqls.andEqualTo(AuthRole::getStatus, request.getStatus());
        }
        return Example.builder(AuthRole.class).where(weekendSqls).build();
    }
}
