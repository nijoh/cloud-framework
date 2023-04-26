package com.cloud.framework.auth.config;

import com.cloud.framework.model.common.base.CreateTime;
import com.cloud.framework.model.common.base.UpdateTime;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * Mybatis拦截器
 * 填充日期属性
 *
 * @description @CreateTime 插入创建时间
 * @description @UpdateTime 插入更新时间
 */
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class GenerateTimeIntercepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // sql 类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数
        Object parameter = invocation.getArgs()[1];
        // 获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            //判断属性是否有Create注解
            if (field.getAnnotation(CreateTime.class) != null) {
                // insert 语句插入
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, LocalDateTime.now());
                }
            }
            //判断属性是否有update注解
            if (field.getAnnotation(UpdateTime.class) != null) {
                // update 语句插入
                if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, LocalDateTime.now());
                }
            }
        }
        return invocation.proceed();
    }
}
