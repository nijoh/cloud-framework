package com.cloud.framework.model.common.base;

import java.io.Serializable;

/**
 * @author nijo_h
 * * @date 2023/11/4 23:15
 */
public class BasePage implements Serializable {
    private static final long serialVersionUID = -7933647197071772621L;
    /**
     * 页面
     * */
    private int pageNum=1;

    /**
     * 每页条数
     * */
    private int pageSize=10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
