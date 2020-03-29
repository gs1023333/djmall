package com.dj.mall.entity.auth.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-27 下午 03:04
 */
@Data
@Accessors(chain = true)
@TableName("djmall_auth_role")
public class RoleEntity implements Serializable {
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    @Mapping("roleId")
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 删除状态 1:正常,0:删除
     */
    private Integer isDel;
}
