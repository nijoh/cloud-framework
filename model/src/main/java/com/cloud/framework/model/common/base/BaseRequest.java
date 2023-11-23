package com.cloud.framework.model.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author nijo_h
 * * @date 2023/11/14 20:01
 */
@Getter
@Setter
public class BaseRequest implements Serializable {
    /**
     * serialVersionUID
     * */
    private static final long serialVersionUID = 1445421334663630559L;

    /**
     * 业务号
     */
    private String BizNo;
}
