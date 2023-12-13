package com.cloud.framework.auth.config.mybatis;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class QueryInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        //获取StatementHandler构造器
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        // 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
        MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
        SqlCommandType commandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.SELECT.equals(commandType)) {
            String sqlId = mappedStatement.getId();
            BoundSql boundSql = delegate.getBoundSql();
            String sql = boundSql.getSql();
            Statement statement = CCJSqlParserUtil.parse(sql);
            Select select = (Select) statement;
            PlainSelect selectBody = (PlainSelect) select.getSelectBody();
            addWhere(selectBody);
            ReflectUtil.setFieldValue(boundSql, "sql", statement.toString());

        }
        return invocation.proceed();

    }

    //增加条件
    private void addWhere(PlainSelect selectBody) {
        try {
            Table fromItem = (Table) selectBody.getFromItem();
            String name = fromItem.getName();
            if (!StringUtils.equals(name, "auth_ms")) {
                String stringExpression = "";
                try {
                    EqualsTo where = (EqualsTo) selectBody.getWhere();
                    stringExpression = where.getStringExpression();
                } catch (Exception e) {
                    stringExpression = selectBody.getWhere() == null ? stringExpression : selectBody.getWhere().toString();
                }
                //如果字段搜索条件为空则搜索字段为空或指定数据
                StringBuilder sqlFilter = new StringBuilder(128);
                if (stringExpression.indexOf("ms_domain") == -1) {
                    sqlFilter.append(String.format("ms_domain='%s'", AuthUserContextHolder.getCurrentMsDomain()));
                    buildWhereClause(selectBody, sqlFilter.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //多表查询时由于不是最后一层，获取不到Table，继续获取子表
            SubSelect ss = (SubSelect) selectBody.getFromItem();
            PlainSelect subSelect = (PlainSelect) ss.getSelectBody();
            addWhere(subSelect);
        }
    }


    private void buildWhereClause(PlainSelect select, String dataFilter) throws JSQLParserException {
        if (select.getWhere() == null) {
            select.setWhere(CCJSqlParserUtil.parseCondExpression(dataFilter));
        } else {
            AndExpression and = new AndExpression(
                    CCJSqlParserUtil.parseCondExpression(dataFilter), select.getWhere());
            select.setWhere(and);
        }
    }
}
