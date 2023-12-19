package com.cloud.framework.auth.utils.convert;

import com.cloud.framework.auth.pojo.AccountUser;
import com.cloud.framework.auth.pojo.request.RegistAccountUserRequest;
import com.cloud.framework.integrate.auth.AuthUserContextHolder;
import com.cloud.framework.model.auth.result.AccountUserDTO;
import com.cloud.framework.model.common.enums.BaseStatusEnum;
import com.cloud.framework.utils.GenerateUtil;
import com.cloud.framework.utils.PasswordEncrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountUser转换类
 * @author nijo_h
 * * @date 2023/11/2 22:32
 */
public class AccountUserConvert {
    /**
     * 请求转换DO
     * @param request 请求参数
     * @return
     */
    public static AccountUser buildConverDOFromRequst(RegistAccountUserRequest request) {
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(request, accountUser);
        accountUser.setId(GenerateUtil.generateAccountId());
        accountUser.setPassword(bcryptPasswordEncoder.encode(PasswordEncrypt.encryptSHA256(request.getPassword())));
        accountUser.setMsDomain(AuthUserContextHolder.getCurrentMsDomain());
        accountUser.setOperator(AuthUserContextHolder.getOperate());
        accountUser.setStatus(BaseStatusEnum.NORMAL.getCode());
        return accountUser;
    }

    /**
     * 转换DTO
     * @param accountUser DO数据
     * @return
     */
    public static AccountUserDTO converToDTOModel(AccountUser accountUser) {
        AccountUserDTO accountUserDTO = new AccountUserDTO();
        accountUserDTO.setEmail(accountUser.getEmail());
        accountUserDTO.setPhone(accountUser.getPhone());
        accountUserDTO.setCreateTime(accountUser.getCreateTime());
        accountUserDTO.setLastLoginTime(accountUser.getLastLoginTime());
        accountUserDTO.setLoginIp(accountUser.getLoginIp());
        accountUserDTO.setStatus(accountUser.getStatus());
        accountUserDTO.setMsDomain(accountUser.getMsDomain());
        return accountUserDTO;
    }

    /**
     * 转换List<DTO>
     * @param accountUserList List DO数据
     * @return
     */
    public static List<AccountUserDTO> converToDTOFromList(List<AccountUser> accountUserList) {
        List<AccountUserDTO> accountUserDTOList =new ArrayList<>();
        for(AccountUser accountUser:accountUserList){
            accountUserDTOList.add(converToDTOModel(accountUser));
        }
        return accountUserDTOList;
    }
}
