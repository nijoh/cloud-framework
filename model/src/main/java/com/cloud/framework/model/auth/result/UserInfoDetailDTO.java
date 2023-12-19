package com.cloud.framework.model.auth.result;

import com.cloud.framework.model.common.enums.BaseStatusEnum;
import com.cloud.framework.model.common.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户详细信息数据
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDetailDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4098586623878278089L;

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
     * 员工头像
     */
    private String staffImage;

    /**
     * 员工性别
     * @see GenderEnum
     */
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 角色名称
     * */
    private String roleName;
}
