package com.cloud.framework.auth;


import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.SaveAccountUserRequest;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.utils.GenerateUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AccountUserTest {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private AccountUserService accountUserService;

    @Test
    void testInsert(){
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        AccountUser accountUser=new AccountUser();
        accountUser.setEmail("root@163.com");
        accountUser.setId(GenerateUtil.generateAccountId());
        accountUser.setPassword(bcryptPasswordEncoder.encode(PasswordEncrypt.encryptSHA256("nijo_h")));
        accountUser.setPhone("13881844088");
        accountUser.setUsername("root");
        accountUserMapper.insertSelective(accountUser);
    }

    @Test
    void testSaveAccountUser(){
        SaveAccountUserRequest request =new SaveAccountUserRequest();
        request.setPhone("13881844089");
        request.setEmail("root@164.com");
        request.setPassword("123124");
        request.setUsername("test");
        accountUserService.saveAccountUser(request);
    }

}
