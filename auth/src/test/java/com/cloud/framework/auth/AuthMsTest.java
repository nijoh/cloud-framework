package com.cloud.framework.auth;

import com.cloud.framework.auth.dal.AuthMsMapper;
import com.cloud.framework.auth.pojo.AuthMs;
import com.cloud.framework.integrate.cache.RedisCacheTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class AuthMsTest {
    @Autowired
    private AuthMsMapper authMsMapper;

    @Autowired
    private RedisCacheTemplate redisCacheTemplate;

    @Test
    void testInsert() {
        int result = authMsMapper.insert(AuthMs.builder().msName("测试").msDomain("/test").build());
        System.out.println(result);
    }

//    @Test
//    void updateById(){
//        int result = authMsMapper.updateById(AuthMs.builder().msName("更新测试").id(1).build());
//        System.out.println(result);
//    }

    @Test
    void testBigKeyRedis() {
        //一个hash 存100个filed 预估1000个filed 去模1000 key:bigKeyHash-x
        String testKey="第52个";
        String value="测试值";
        Map<String,Object> map=new HashMap<>();
        map.put(testKey,value);
        String key="bigKeyHash-";

        int index=testKey.hashCode()%100;
        System.out.println("hash值:"+testKey.hashCode()+",取模100下标:"+index);

        //set
        redisCacheTemplate.add(key+index,map);


        //获取
        int getIndex=testKey.hashCode()%100;
        String mapString = redisCacheTemplate.getMapString(key + index, testKey);
        System.out.println("获取key："+mapString);
    }


}
