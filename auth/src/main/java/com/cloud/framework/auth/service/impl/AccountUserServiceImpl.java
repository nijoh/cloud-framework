package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.auth.utils.TransactionService;
import com.cloud.framework.model.auth.request.SaveAccountUserRequest;
import com.cloud.framework.model.common.CloudConstant;
import com.cloud.framework.utils.AsserUtil;
import com.cloud.framework.utils.GenerateUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 账户信息ServiceImpl
 */
@Service
public class AccountUserServiceImpl implements AccountUserService {
    //账户信息Mapper
    @Autowired
    private AccountUserMapper accountUserMapper;

    //DB事务模版
    @Autowired
    private TransactionService transactionService;

    /**
     * @see com.cloud.framework.auth.service.AccountUserService#findAccountUserByEmail(String)
     */
    @Override
    public AccountUser findAccountUserByEmail(String email) {
        AccountUser accountUser = new AccountUser();
        accountUser.setEmail(email);
        return accountUserMapper.selectOne(accountUser);
    }

    /**
     * @see com.cloud.framework.auth.service.AccountUserService#saveAccountUser(SaveAccountUserRequest)
     */
    @Override
    public void saveAccountUser(SaveAccountUserRequest request) {
        AccountUser accountUser = this.buildConverDO(request);
        transactionService.processor(new TransactionProcessor() {
            @Override
            public void processor() {
                int result = accountUserMapper.insertSelective(accountUser);
                AsserUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, "保存账户信息表失败");
            }
        });
    }

    private AccountUser buildConverDO(SaveAccountUserRequest request) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(request, accountUser);
        accountUser.setId(GenerateUtil.generateAccountId());
        accountUser.setPassword(bcryptPasswordEncoder.encode(PasswordEncrypt.encryptSHA256(request.getPassword())));
        return accountUser;
    }
}
