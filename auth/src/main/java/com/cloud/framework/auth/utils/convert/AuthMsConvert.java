package com.cloud.framework.auth.utils.convert;

import com.cloud.framework.auth.pojo.AuthMs;
import com.cloud.framework.model.auth.result.AuthMsDTO;

/**
 * 系统模型转换器
 */
public class AuthMsConvert {
    /**
     * 转换DTO
     * @param authMs DO数据
     * @return
     */
    public static AuthMsDTO converToDTOModel(AuthMs authMs){
        AuthMsDTO authMsDTO = new AuthMsDTO();
        authMsDTO.setMsId(authMs.getId());
        authMsDTO.setMsDomain(authMs.getMsDomain());
        return authMsDTO;

    }
}
