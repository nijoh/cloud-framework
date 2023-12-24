package com.cloud.framework.model.common.constant;

/**
 * 常量定义
 */
public class CloudConstant {

    //超级用户角色名称
    public static String SUPER_ADMINISTRATOR_ROLE_NAME = "超级管理员";
    /********************************* HTTP请求  **********************************/

    //JWT请求头
    public static String REQUEST_AUTHORIZATION_TOKEN = "AuthorizationToken";

    //response返回类型
    public static String RESPONSE_CONTENTTYPE = "application/json;charset=UTF-8";

    //用户Email标识
    public static String REQUEST_HEADER_ACCOUNTUSEREMAIL = "AccountUserEmail";

    //所属系统域
    public static String REQUEST_HEADER_DOMAIN = "Domain";

    /********************************* DB错误   **********************************/
    public static String DB_INSERT_ERROR = "添加数据失败";

    public static String DB_MODIFY_ERROR = "修改数据失败";

    public static String DB_DELETE_ERROR = "删除数据失败";


    /********************************* 系统模版   **********************************/
    //auth模块
    public static String AUTH_HMODEL = "auth_model";
    //未查询到用户
    public static String NOT_ACCOUNTUSER = "未查询到用户信息";


    /**************************** 系统属性 ************************************/
    //系统默认操作人员
    public static String SYSTEM_OPERATE = "SYSTEM";

    //表字段msDomain
    public static String TABLE_NAME_MSDOMAIN = "msDomain";

    //表字段operator
    public static String TABLE_NAME_OPERATOR = "operator";


}