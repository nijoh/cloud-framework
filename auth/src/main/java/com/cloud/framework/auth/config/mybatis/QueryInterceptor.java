package com.cloud.framework.auth.config.mybatis;

import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Component
@Intercepts(@Signature(type = Executor.class, method = "query", args ={MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class, CacheKey.class,BoundSql.class}))
public class QueryInterceptor implements Interceptor {



    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
            BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
            String originalSql = boundSql.getSql();
            String newSql = appendCondition(originalSql );
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), newSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new SqlSource() {
                @Override
                public BoundSql getBoundSql(Object parameterObject) {
                    return newBoundSql;
                }
            });
            invocation.getArgs()[0] = newMs;
            return ((Executor) invocation.getTarget()).query(newMs, args[1], (RowBounds) args[2], (ResultHandler) args[3]);
        }
        return invocation.proceed();
    }

    private String appendCondition(String sql) {
        //跳过系统表查询
        if(sql.contains("auth_ms")){
            return sql;
        }
        if (sql.toLowerCase().contains("where")) {
            String appendSql=String.format("and ms_domain='%s'",AuthUserContextHolder.getCurrentMsDomain());
            return sql + appendSql;
        } else {
            String appendSql=String.format("where ms_domain='%s'",AuthUserContextHolder.getCurrentMsDomain());
            return sql + appendSql;
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            builder.keyProperty(ms.getKeyProperties()[0]);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

}
