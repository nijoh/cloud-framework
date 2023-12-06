package com.cloud.framework.auth.service;

import com.cloud.framework.model.auth.result.AuthMsDTO;

/**
 * 系统查询
 */
public interface AuthMsQueryService {
    /**
     * domain查询所属系统
     * @param msDomain
     * @return
     */
    AuthMsDTO queryAuthMsByDomain(String msDomain);
}
