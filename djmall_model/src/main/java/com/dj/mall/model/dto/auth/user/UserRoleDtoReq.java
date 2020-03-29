package com.dj.mall.model.dto.auth.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:17
 */
@Data
public class UserRoleDtoReq implements Serializable {

    private Integer userRoleId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;
}
