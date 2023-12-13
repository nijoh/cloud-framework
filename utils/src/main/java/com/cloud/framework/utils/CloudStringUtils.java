package com.cloud.framework.utils;

/***
 * 字符串工具类
 */
public class CloudStringUtils {
    /**
     * 构造模糊查询字符串 %字符串%
     * @param params
     * @return
     */
    public static String GeneratLikeString(final String params){
        StringBuilder builder=new StringBuilder();
        builder.append("%").append(params).append("%");
        return builder.toString();
    }
}
