package com.cloud.framework.model.common.constant;

/**
 * 常量定义
 * */
public class CloudConstant {
    /********************************* HTTP请求  **********************************/

    //JWT请求头
    public static String REQUEST_JWT_HEADER = "Authorization";

    //response返回类型
    public static String RESPONSE_CONTENTTYPE = "application/json;charset=UTF-8";

    /********************************* DB错误   **********************************/
    public static String DB_INSERT_ERROR = "添加数据失败";

    /********************************* 系统模版   **********************************/
    //auth模块
    public static String AUTH_HMODEL = "auth_model";
    //未查询到用户
    public static String NOT_ACCOUNTUSER = "未查询到用户信息";
}