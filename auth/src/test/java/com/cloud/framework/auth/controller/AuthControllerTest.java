package com.cloud.framework.auth.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 系统验、授权资源Controller-单元测试
 * @author nijo_h
 * @date 2023/3/21 11:22 下午
 */
@SpringBootTest
@WebAppConfiguration
public class AuthControllerTest {
    @Autowired
    private AuthController authController;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void login() throws Exception {
        LoginUserRequest request = new LoginUserRequest();
        request.setEmail("root@163.com");
        request.setPassWord("root");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void regist() throws Exception {
        RegistAccountUserRequest request =new RegistAccountUserRequest();
        request.setEmail("root@163.com");
        request.setPassword("root");
        request.setPhone("88888888");
        request.setUsername("超级管理");
        request.setName("超级管理");
        request.setGender('1');

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/regist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
