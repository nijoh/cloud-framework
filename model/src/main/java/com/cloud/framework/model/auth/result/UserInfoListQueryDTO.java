package com.cloud.framework.model.auth.result;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户列表查询
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoListQueryDTO implements Serializable {
    private static final long serialVersionUID = 6653389623388503371L;
    /**
     * 员工ID
     */
    private Integer staffId;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     *  状态
     * @see BaseStatusEnum
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /**
     * 最后一次登陆时间
     */
    private LocalDateTime lastLoginTime;
}
