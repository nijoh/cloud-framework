package com.cloud.framework.model.common.result;


import java.io.Serializable;

/**
 * 统一返回包装类
 */
public class BaseResult<T> implements Serializable {
    //是否成功
    private Boolean success;
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //数据
    private T content;

    public BaseResult() {
    }

    //自定义返回结果的构造方法
    public BaseResult(Boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.content = data;

    }

    //成功
    public BaseResult success() {
        this.success = true;
        this.code = 200;
        this.msg="成功";
        return this;
    }

    public BaseResult success(T data) {
        this.success = true;
        this.code = 200;
        this.content = data;
        this.msg="成功";
        return this;
    }

    //失败
    public BaseResult fail(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
        this.success = false;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
