package com.cloud.framework.auth.controller;

import com.cloud.framework.auth.pojo.request.QueryUserReuqest;
import com.cloud.framework.auth.service.AccountUserQueryService;
import com.cloud.framework.model.auth.result.UserInfoDetailDTO;
import com.cloud.framework.model.auth.result.UserInfoListQueryDTO;
import com.cloud.framework.model.common.base.ApiProcessor;
import com.cloud.framework.model.common.base.BusinessTemplate;
import com.cloud.framework.model.common.result.BaseResult;
import com.cloud.framework.utils.AssertUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 账户信息查询
 * @author nijo_h
 * * @date 2023/11/2 22:15
 */
@RequestMapping("/account/user")
@RestController
public class AccountUserQueryController {
    //用户查询Service
    @Autowired
    private AccountUserQueryService accountUserQueryService;

    /**
     * 分页查询用户账户信息
     * @param queryUserReuqest 查询请求参数
     * @return
     */
    @PostMapping("/queryPage")
    public BaseResult<PageInfo<UserInfoListQueryDTO>> queryUserPage(@RequestBody QueryUserReuqest queryUserReuqest){
        BaseResult<PageInfo<UserInfoListQueryDTO>> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {
            @Override
            public void processor() {
                result.setContent(accountUserQueryService.queryPage(queryUserReuqest));
            }
        });
        return result;
    }

    /**
     * 查询员工详细信息
     * @param staffId 员工ID
     * @return
     */
    @GetMapping("/queryDetail")
    public BaseResult<UserInfoDetailDTO> queryUserInfoDetail(Integer staffId){
        BaseResult<UserInfoDetailDTO> result =new BaseResult<>();
        ApiProcessor.processor(result, new BusinessTemplate() {

            @Override
            public void checkParam() {
                AssertUtil.isTrue(Objects.nonNull(staffId),"未选择查询员工数据");
            }

            @Override
            public void processor() {
                result.setContent(accountUserQueryService.queryUserInfoDetail(staffId));
            }
        });
        return result;
    }



}
