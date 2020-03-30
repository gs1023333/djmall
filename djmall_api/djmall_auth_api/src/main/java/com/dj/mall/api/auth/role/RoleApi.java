package com.dj.mall.api.auth.role;

import com.dj.mall.model.dto.auth.role.RoleDtoReq;
import com.dj.mall.model.dto.auth.role.RoleDtoResp;
import com.dj.mall.model.dto.auth.role.RoleResourceDtoResp;

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

    /**
     * 功能描述: 判断角色名是否存在
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 12:49
     */
    RoleDtoResp findByName(String roleName) throws  Exception;

    /**
     * 功能描述: 新增角色
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 12:54
     */
    void addRole(RoleDtoReq roleDtoReq) throws  Exception;


    /**
     * 功能描述: 根据ID查找角色
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 12:59
     */
    RoleDtoResp findById(Integer roleId) throws  Exception;

    /**
     * 功能描述: 根据ID修改角色
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:05
     */
    void updateRoleById(RoleDtoReq roleDtoReq) throws Exception;

    /**
     * 功能描述: 根据ID删除角色
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:11
     */
    void delRoleById(Integer roleId) throws Exception;

    /**
     * 功能描述: 修改关联资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 04:15
     */
    void relatedResource(Integer roleId, Integer[] resourceIds) throws Exception;

    /**
     * 功能描述: 展示关联资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 04:18
     */
    List<RoleResourceDtoResp> showResource(Integer roleId) throws Exception;
}
