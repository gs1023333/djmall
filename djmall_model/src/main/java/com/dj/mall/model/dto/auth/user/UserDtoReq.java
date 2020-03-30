package com.dj.mall.model.dto.auth.user;

import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:11
 */
@Data
public class UserDtoReq implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户激活状态 1:激活,2:未激活
     */
    private Integer status;

    /**
     * 用户级别 1:管理员,2:商户,3:买家,:4无
     */
    private Integer userLevel;

    /**
     * 用户性别 1:男,2:女
     */
    private Integer userSex;

    /**
     * 用户注册时间
     */
    private Date createTime;

    /**
     * 用户最后登录时间
     */
    private Date endTime;

    /**
     * MD5盐
     */
    private String salt;

    /**
     * 系统重置的密码
     */
    private String resetPwd;

    /**
     * 用户接收的验证码
     */
    private Integer userCode;

    /**
     * 验证码有效时间
     */
    private Date codeTime;

    /**
     * 用户删除状态 1:正常,0:删除
     */
    private Integer isDel;

    /**
     * 用户权限集合
     */
    private List<ResourceDtoResp> authList;
    /**
     * 角色ID
     */
    private Integer roleId;
}
