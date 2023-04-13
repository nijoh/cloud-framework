package com.cloud.framework.auth.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.framework.auth.pojo.request.AddAuthMenuRequest;
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
 * 菜单管理Test
 * @author nijo_h
 * * @date 2023/3/30 11:49 下午
 */
@SpringBootTest
@WebAppConfiguration
public class AuthMenuControllerTest {
    @Autowired
    private  AuthMenuController authMenuController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authMenuController).build();
    }


    @Test
    public void addMenu() throws Exception {
        AddAuthMenuRequest request =new AddAuthMenuRequest();
        request.setMenuName("菜单管理");
        request.setMenuUri("/authMenu");
        request.setStatus(true);
        request.setParentId(1);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/authMenu/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
