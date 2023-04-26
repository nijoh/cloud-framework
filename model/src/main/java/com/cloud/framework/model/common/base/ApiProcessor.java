package com.cloud.framework.model.common.base;

import com.cloud.framework.model.common.result.BaseResult;
import com.cloud.framework.model.common.enums.HttpEnum;

/**
 * 业务逻辑处理模版
 * */
public class ApiProcessor {

    public static <T extends BaseResult> T processor(T result, BusinessTemplate template){
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
