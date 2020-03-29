package com.dj.mall.entity.auth.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:11
 */
@Data
@Accessors(chain = true)
@TableName("djmall_auth_user_role")
public class UserRoleEntity implements Serializable {
    /**
     ** ID
     */
    @TableId(type = IdType.AUTO)
    @Mapping("userRoleId")
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;
}
