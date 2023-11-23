package com.cloud.framework.auth;


import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AccountUserMangeService;
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
    private AccountUserMangeService accountUserMangeService;

    @Test
    void testInsert(){
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        AccountUser accountUser=new AccountUser();
        accountUser.setEmail("root@163.com");
        accountUser.setId(GenerateUtil.generateAccountId());
        accountUser.setPassword(bcryptPasswordEncoder.encode(PasswordEncrypt.encryptSHA256("root")));
        accountUser.setPhone("88888888");
        accountUser.setUsername("超级管理");
        accountUserMapper.insertSelective(accountUser);
    }

    @Test
    void testSaveAccountUser(){
        RegistAccountUserRequest request =new RegistAccountUserRequest();
        request.setEmail("root@163.com");
        request.setPassword("root");
        request.setPhone("88888888");
        request.setUsername("超级管理");
        request.setName("超级管理");
        request.setGender('1');
        accountUserMangeService.saveAccountUser(request);
    }

}
