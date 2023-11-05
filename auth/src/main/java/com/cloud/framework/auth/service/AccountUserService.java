package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.github.pagehelper.PageInfo;


/**
 * 账户信息Service
 * */
public interface AccountUserService {
    /**
     * 通过电子邮箱查询账户信息表
     *
     * @param email 电子邮箱
     * @return AccountUser 账户信息
     */
    AccountUser findAccountUserByEmail(String email);


    /**
     * 保存账户信息表
     *
     * @param request 请求参数
     */
    void saveAccountUser(RegistAccountUserRequest request);

    /**
     * 分页查询用户信息
     * @param queryUserReuqest 查询条件
     * @return
     */
    PageInfo queryPage(QueryUserReuqest queryUserReuqest);

}
