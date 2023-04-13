package com.cloud.framework.auth.service.impl;

import com.cloud.framework.auth.dal.AccountUserMapper;
import com.cloud.framework.auth.dal.StaffInfoMapper;
import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.StaffInfo;
import com.cloud.framework.auth.pojo.request.LoginUserRequest;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.auth.service.AuthService;
import com.cloud.framework.auth.utils.JwtUtil;
import com.cloud.framework.auth.utils.TransactionProcessor;
import com.cloud.framework.auth.utils.TransactionService;
import com.cloud.framework.model.common.CloudConstant;
import com.cloud.framework.utils.AsserUtil;
import com.cloud.framework.utils.GenerateUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 系统授权ServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private StaffInfoMapper staffInfoMapper;

    //DB事务执行模版
    @Autowired
    private TransactionService transactionService;

    /**
     * @see com.cloud.framework.auth.service.AuthService#login(LoginUserRequest request)
     */
    @Override
    public String login(LoginUserRequest request) {
        //加密
        String encryptPassword = PasswordEncrypt.encryptSHA256(request.getPassWord());
        //使用security框架自带的验证token生成器  也可以自定义。
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), encryptPassword);
        Authentication authenticate = authenticationManager.authenticate(token);
        //放入Security上下文
        SecurityContextHolder.getContext().setAuthentication(authenticate);

       // AccountUser accountUser=(AccountUser)authenticate.getPrincipal();
        //生成Token
        String jwt = JwtUtil.createJWT(request.getEmail(), JwtUtil.getExpirationTime());

        return jwt;
    }

    /**
     * @see com.cloud.framework.auth.service.AuthService#regist(RegistAccountUserRequest request)
     */
    @Override
    public void regist(RegistAccountUserRequest request) {
        AccountUser accountUser = converAccountUser(request);
        StaffInfo staffInfo = converStaffInfo(request, accountUser.getId());
        transactionService.processor(new TransactionProcessor() {

            @Override
            public void processor() {
                int result = accountUserMapper.insertSelective(accountUser);
                AsserUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, CloudConstant.DB_INSERT_ERROR);
                result=staffInfoMapper.insertSelective(staffInfo);
                AsserUtil.isTrue(result > 0, CloudConstant.AUTH_HMODEL, CloudConstant.DB_INSERT_ERROR);
            }
        });
    }

    /**
     * 模型转换表
     *
     * @param request 模型
     * @return AccountUser 表对象
     */
    private AccountUser converAccountUser(RegistAccountUserRequest request) {
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(request, accountUser);
        //GenerateId
        accountUser.setId(GenerateUtil.generateAccountId());
        accountUser.setPassword(new BCryptPasswordEncoder()
                .encode(PasswordEncrypt.encryptSHA256(request.getPassword())));
        return accountUser;
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
