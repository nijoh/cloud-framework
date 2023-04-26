package com.cloud.framework.auth.config;

import com.cloud.framework.model.common.enums.HttpEnum;
import com.cloud.framework.model.common.result.BaseResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web请求异常处理器
 *
 * @author nijo_h
 * @date 2023/3/22 10:27 下午
 */
@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
    /**
     * 参数校验异常
     *
     * @param exception 异常信息
     * @return Result 异常提示
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult<String> MethodArgumentNotValidException(BindException exception) {
        return new BaseResult().fail(HttpEnum.BUSINESS.getCode(),exception.getFieldError().getDefaultMessage());
    }
}
