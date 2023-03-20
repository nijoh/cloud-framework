package com.cloud.framework.utils;

import java.text.SimpleDateFormat;

/**
 * 系统生成工具
 */
public class GenerateUtil {
    /**
     * 生成账户表ID
     *
     * @description: 根据时间戳+随机数 并发不高情况可以
     */
    public static String generateAccountId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String number = sdf.format(System.currentTimeMillis());//202211041552160219
        //补充一个三位随机数
        int x = (int) (Math.random() * 900) + 100;
        return number + x;
    }
}
