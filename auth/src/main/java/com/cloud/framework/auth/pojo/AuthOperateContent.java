package com.cloud.framework.auth.pojo;

import java.util.Map;

public class AuthOperateContent<T> {
    /**
     * 上下文对象
     */
    T dataContent;

    /**
     * 扩展信息
     */
    private Map<String,String> extendInfo;

    public T getDataContent() {
        return dataContent;
    }

    public void setDataContent(T dataContent) {
        this.dataContent = dataContent;
    }

    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(Map<String, String> extendInfo) {
        this.extendInfo = extendInfo;
    }
}
