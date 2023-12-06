package com.cloud.framework.model.auth.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class AuthMsDTO implements Serializable {
    /**
     * 系统ID
     */
    private Integer msId;

    /**
     * 系统域名
     */
    private String msDomain;
}
