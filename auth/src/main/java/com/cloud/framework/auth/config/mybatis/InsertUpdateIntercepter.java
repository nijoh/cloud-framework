package com.cloud.framework.auth.config.mybatis;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.integrate.auth.AuthUserContextHolder;
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

import static com.cloud.framework.model.common.constant.CloudConstant.TABLE_NAME_MSDOMAIN;
import static com.cloud.framework.model.common.constant.CloudConstant.TABLE_NAME_OPERATOR;

/**
 * Mybatis拦截器
 * 填充日期属性
 *
 * @description @CreateTime 插入创建时间
 * @description @UpdateTime 插入更新时间
 */
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class InsertUpdateIntercepter implements Interceptor {

    @Override
    public Object intercept(Invocation invocation)   throws Throwable{
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // sql 类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数
        Object parameter = invocation.getArgs()[1];
        // 获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            // insert 语句插入
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                //系统所属域
                if(StringUtils.equals(field.getName(),TABLE_NAME_MSDOMAIN)){
                    setFieldValue(field,parameter,AuthUserContextHolder.getCurrentMsDomain());
                }

                //判断属性是否有Create注解
                if (field.getAnnotation(CreateTime.class) != null) {
                    setFieldValue(field,parameter, LocalDateTime.now());
                }
            }

            //update 语句插入
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //判断属性是否有update注解
                if (field.getAnnotation(UpdateTime.class) != null) {
                    setFieldValue(field,parameter, LocalDateTime.now());
                }
            }

            //操作人
            if(StringUtils.equals(field.getName(),TABLE_NAME_OPERATOR)){
                setFieldValue(field,parameter,AuthUserContextHolder.getOperate());
            }
        }
        return invocation.proceed();
    }


    /**
     * 修改Filed属性
     * @param field 字段
     * @param parameter 获取参数
     * @param value 修改的值
     * @throws Throwable
     */
    private void setFieldValue(Field field,Object parameter,Object value) throws Throwable{
        field.setAccessible(true);
        field.set(parameter, value);
    }
}
