package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthMsMenuMapper;
import com.cloud.framework.auth.pojo.AuthMsMenu;
import com.cloud.framework.auth.pojo.request.AddAuthMenuRequest;
import com.cloud.framework.auth.service.AuthMenuService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.auth.utils.TransactionService;
import com.cloud.framework.model.common.CloudConstant;
import com.cloud.framework.utils.AsserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单管理impl
 * @author nijo_h
 * * @date 2023/3/30 11:41 下午
 */
@Service
public class AuthMenuServiceImpl implements AuthMenuService {
    @Autowired
    private AuthMsMenuMapper menuMapper;

    //DB事务执行模版
    @Autowired
    private TransactionService transactionService;

    /**
     * @see com.cloud.framework.auth.service.AuthMenuService#addMenu(AddAuthMenuRequest)
     */
    @Override
    public void addMenu(AddAuthMenuRequest request) {
        AuthMsMenu authMsMenu = this.converAuthMsMenu(request);

        transactionService.processor(new TransactionProcessor() {

            @Override
            public void processor() {
                int result =menuMapper.insertSelective(authMsMenu);
                AsserUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, CloudConstant.DB_INSERT_ERROR);
            }
        });
    }

    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AuthMsMenu 表对象
     */
    private AuthMsMenu converAuthMsMenu(AddAuthMenuRequest request){
        AuthMsMenu authMsMenu=new AuthMsMenu();
        BeanUtils.copyProperties(request,authMsMenu);
        //todo 获取当前系统ms_id、当前登录的操作人
        authMsMenu.setMsId(1);
        authMsMenu.setUpdateBy(1);
        return authMsMenu;
    }
}
