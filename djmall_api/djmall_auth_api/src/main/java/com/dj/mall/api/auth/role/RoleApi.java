package com.dj.mall.api.auth.role;

import com.dj.mall.model.dto.auth.role.RoleDtoResp;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:08
 */
public interface RoleApi {

    /**
     * 功能描述: 获取所有角色
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 04:16
     */
    List<RoleDtoResp> getList() throws Exception;

}
