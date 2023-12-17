package com.cloud.framework.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.AuthOperateContent;
import com.cloud.framework.auth.pojo.StaffInfo;
import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AbstractBaseService;
import com.cloud.framework.auth.service.AuthService;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.auth.utils.convert.AccountUserConvert;
import com.cloud.framework.integrate.auth.TokenUtil;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.auth.result.LoginResultDTO;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.utils.AssertUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.cloud.framework.auth.utils.convert.AccountUserConvert.buildConverDOFromRequst;
import static com.cloud.framework.model.common.constant.OperateTypeConstant.ACCOUNT_USER_CREATE;

/**
 * 系统授权ServiceImpl
 */
@Service
public class AuthServiceImpl extends AbstractBaseService implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private StaffInfoMapper staffInfoMapper;


    /**
     * @see com.cloud.framework.auth.service.AuthService#login(LoginUserRequest request)
     */
    @Override
    public LoginResultDTO login(LoginUserRequest request) {
        LoginResultDTO result =new LoginResultDTO();
        //加密
        String encryptPassword = PasswordEncrypt.encryptSHA256(request.getPassWord());
        //使用security框架自带的验证token生成器  也可以自定义。
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), encryptPassword);
        Authentication authenticate = authenticationManager.authenticate(token);
        //放入Security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        //账户信息
        AccountUserDTO accountUserDTO =AccountUserConvert.converToDTOModel((AccountUser) authenticate.getPrincipal());
        //生成Token
        String jwtToken = TokenUtil.createJWT(JSON.toJSONString(accountUserDTO), TokenUtil.getExpirationTime());
        BeanUtils.copyProperties(accountUserDTO,result);
        result.setAuthorizationToken(jwtToken);
        return result;
    }

    /**
     * @see com.cloud.framework.auth.service.AuthService#regist(RegistAccountUserRequest request)
     */
    @Override
    public void regist(RegistAccountUserRequest request) {
        AccountUser accountUser = buildConverDOFromRequst(request);
        StaffInfo staffInfo = converStaffInfo(request, accountUser.getId());
        transactionService.processor(new TransactionProcessor<AuthOperateContent>() {

            @Override
            public AuthOperateContent saveOrder() {
                operateOrderService.createOperateOrder(request.getBizNo(),ACCOUNT_USER_CREATE);
                return new AuthOperateContent();
            }

            @Override
            public void processor(AuthOperateContent content) {
                int result = accountUserMapper.insertSelective(accountUser);
                AssertUtil.isTrue(result > 0,  CloudConstant.DB_INSERT_ERROR);
                result=staffInfoMapper.insertSelective(staffInfo);
                AssertUtil.isTrue(result > 0, CloudConstant.DB_INSERT_ERROR);
            }
        });
    }



    /**
     * 模型转换表
     *
     * @param request       模型
     * @param accountUserId 账户表ID
     * @return StaffInfo 表对象
     */
    private StaffInfo converStaffInfo(RegistAccountUserRequest request, String accountUserId) {
        StaffInfo staffInfo = new StaffInfo();
        BeanUtils.copyProperties(request, staffInfo);
        staffInfo.setUid(accountUserId);
        staffInfo.setNickname(request.getUsername());
        return staffInfo;
    }
}
