package com.cloud.framework.auth.service;

import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.github.pagehelper.PageInfo;

/**
 * 查询用户账户信息
 * @author nijo_h
 * * @date 2023/11/14 19:52
 */
public interface AccountUserQueryService {
    /**
     * 通过电子邮箱查询账户信息表
     *
     * @param email 电子邮箱
     * @return AccountUserDTO 账户信息
     */
    AccountUserDTO findAccountUserByEmail(String email);


    /**
     * 通过电子邮箱查询账户信息表
     *
     * @param email 电子邮箱
     * @return AccountUser 账户信息
     */
    AccountUser findAccountUser(String email);

    /**
     * 分页查询用户信息
     * @param queryUserReuqest 查询条件
     * @return
     */
    PageInfo queryPage(QueryUserReuqest queryUserReuqest);
}
