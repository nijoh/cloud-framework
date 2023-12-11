package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthMsMenuMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.request.AuthMenuAddRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthMenuService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单管理impl
 * @author nijo_h
 * * @date 2023/3/30 11:41 下午
 */
@Service
public class AuthMenuServiceImpl extends AbstractBaseService implements AuthMenuService  {
    @Autowired
    private AuthMsMenuMapper menuMapper;

    /**
     * @see com.cloud.framework.auth.service.AuthMenuService#addMenu(AuthMenuAddRequest)
     */
    @Override
    public void addMenu(AuthMenuAddRequest request) {
        AuthMsMenu authMsMenu = this.converAuthMsMenu(request);
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void processor() {
                int result =menuMapper.insertSelective(authMsMenu);
                AssertUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, CloudConstant.DB_INSERT_ERROR);
            }
        });
    }

    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AuthMsMenu 表对象
     */
    private AuthMsMenu converAuthMsMenu(AuthMenuAddRequest request){
        AuthMsMenu authMsMenu=new AuthMsMenu();
        BeanUtils.copyProperties(request,authMsMenu);
        return authMsMenu;
    }
}
