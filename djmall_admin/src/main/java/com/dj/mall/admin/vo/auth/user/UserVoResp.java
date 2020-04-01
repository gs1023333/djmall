package com.dj.mall.admin.vo.auth.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:41
 */
@Data
public class UserVoResp implements Serializable {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 用户最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyy-MM-dd HH:mm:ss")
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
}
