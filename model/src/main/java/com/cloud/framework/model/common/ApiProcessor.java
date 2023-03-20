package com.cloud.framework.model.common;

/**
 * 业务逻辑处理模版
 * */
public class ApiProcessor {

    public static <T extends Result> T processor(T result, BusinessTemplate template){
        try {
            //校验参数
            template.checkParam();
            //执行业务
            template.processor();
            result.success();
        } catch (RuntimeException e) {
            e.printStackTrace();
            result.fail(HttpEnum.BUSINESS.getCode(),e.getMessage());
            return result;
        }catch (Throwable e){
            e.printStackTrace();
            result.fail(HttpEnum.UNKNOWN.getCode(),e.getMessage());
            return result;
        }
        return result;
    }
}
