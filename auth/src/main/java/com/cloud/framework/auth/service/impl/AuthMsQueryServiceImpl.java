package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AuthMsMapper;
import com.cloud.framework.auth.pojo.AuthMs;
import com.cloud.framework.auth.service.AuthMsQueryService;
import com.cloud.framework.auth.utils.convert.AuthMsConvert;
import com.cloud.framework.model.auth.result.AuthMsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthMsQueryServiceImpl implements AuthMsQueryService {
    @Autowired
    private AuthMsMapper authMsMapper;

    /**
     * @see AuthMsQueryService#queryAuthMsByDomain(String)
     */
    @Override
    public AuthMsDTO queryAuthMsByDomain(String msDomain) {
        AuthMs requst=new AuthMs();
        requst.setMsDomain(msDomain);
        return AuthMsConvert.converToDTOModel(authMsMapper.selectOne(requst));
    }
}
