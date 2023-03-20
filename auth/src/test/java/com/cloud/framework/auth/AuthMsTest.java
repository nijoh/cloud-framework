//package com.cloud.framework.auth;
//
//import com.cloud.framework.dal.interfaces.account.AuthMsMapper;
//import com.cloud.framework.auth.pojo.AuthMs;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class AuthMsTest {
//    @Autowired
//    private AuthMsMapper authMsMapper;
//
//    @Test
//    void testInsert() {
//        int result=authMsMapper.insert(AuthMs.builder().msName("测试").msDomain("/test").build());
//        System.out.println(result);
//    }
//
//    @Test
//    void updateById(){
//        int result = authMsMapper.updateById(AuthMs.builder().msName("更新测试").id(1).build());
//        System.out.println(result);
//    }
//}
