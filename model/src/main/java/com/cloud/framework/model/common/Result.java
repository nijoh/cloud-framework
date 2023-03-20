package com.cloud.framework.model.common;


import java.io.Serializable;

/**
 * 统一返回包装类
 */
public class Result<T> implements Serializable {
    //是否成功
    private Boolean success;
    //状态码
    private Integer code;
    //提示信息
    private String msg;
    //数据
    private T data;

    public Result() {
    }

    //自定义返回结果的构造方法
    public Result(Boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;

    }

    //成功
    public Result success() {
        this.success = true;
        this.code = 200;
        this.msg="成功";
        return this;
    }

    public Result success(T data) {
        this.success = true;
        this.code = 200;
        this.data = data;
        this.msg="成功";
        return this;
    }

    //失败
    public Result fail(Integer code, String msg) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
