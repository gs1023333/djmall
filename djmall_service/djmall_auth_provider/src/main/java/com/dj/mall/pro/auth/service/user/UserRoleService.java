package com.dj.mall.pro.auth.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.entity.auth.user.UserRoleEntity;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:28
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    /**
     * 功能描述: 根据角色ID删除关联表内的内容
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:23
     */
    void delUserRole(Integer roleId) throws Exception;

}
