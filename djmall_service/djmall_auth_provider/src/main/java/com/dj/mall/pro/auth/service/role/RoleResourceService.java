package com.dj.mall.pro.auth.service.role;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.entity.auth.role.RoleResourceEntity;
import com.dj.mall.model.dto.auth.role.RoleResourceDtoResp;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:25
 */
public interface RoleResourceService extends IService<RoleResourceEntity> {

    /**
     * 功能描述: 根据角色id删除关联表内对应的资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:15
     */
    void delRoleResource(Integer roleId) throws Exception;


    /**
     * 功能描述: 根据角色ID查找对应的资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:36
     */
    List<RoleResourceDtoResp> findByRoleId(Integer roleId) throws Exception;

    /**
     * 功能描述: 将新关联的资源加进去
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:49
     */
    void addRoleResource(Integer roleId, Integer resourceId) throws Exception;
}
