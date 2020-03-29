package com.dj.mall.model.dto.auth.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:19
 */
@Data
public class RoleResourceDtoReq implements Serializable {

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 资源ID
     */
    private Integer resourceId;

}
