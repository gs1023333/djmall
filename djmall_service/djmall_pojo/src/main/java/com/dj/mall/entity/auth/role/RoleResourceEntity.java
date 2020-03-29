package com.dj.mall.entity.auth.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:14
 */
@Data
@Accessors(chain = true)
@TableName("djmall_auth_role_resource")
public class RoleResourceEntity implements Serializable {

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 资源ID
     */
    private Integer resourceId;
}
