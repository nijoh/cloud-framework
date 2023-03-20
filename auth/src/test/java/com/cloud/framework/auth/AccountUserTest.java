package com.cloud.framework.auth;


import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.service.AccountUserService;
import com.cloud.framework.model.auth.request.SaveAccountUserRequest;
import com.cloud.framework.utils.GenerateUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class AccountUserTest {
    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private AccountUserService accountUserService;

    @Test
    void testInsert() throws NoSuchAlgorithmException {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        AccountUser build = AccountUser.builder()
                .id(GenerateUtil.generateAccountId())
                .email("root@163.com")
                .password(bcryptPasswordEncoder.encode(PasswordEncrypt.encryptSHA256("huqiliang")))
                .phone("13881844088")
                .username("root").build();
        accountUserMapper.insertSelective(build);
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
