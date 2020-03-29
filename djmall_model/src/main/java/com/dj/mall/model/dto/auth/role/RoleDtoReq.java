package com.dj.mall.model.dto.auth.role;

import lombok.Data;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-27 下午 03:07
 */
@Data
public class RoleDtoReq  implements Serializable {

    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 删除状态 1:正常,0:删除
     */
    private Integer isDel;
}
