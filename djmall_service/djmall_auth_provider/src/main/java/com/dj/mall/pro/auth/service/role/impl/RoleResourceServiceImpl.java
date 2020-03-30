package com.dj.mall.pro.auth.service.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.entity.auth.role.RoleResourceEntity;
import com.dj.mall.mapper.auth.role.RoleResourceMapper;
import com.dj.mall.model.dto.auth.role.RoleResourceDtoResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.pro.auth.service.role.RoleResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:26
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResourceEntity> implements RoleResourceService {
    /**
     * 功能描述: 根据角色id删除关联表内对应的资源
     *
     * @param roleId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:15
     */
    @Override
    public void delRoleResource(Integer roleId) throws Exception {
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        this.remove(queryWrapper);
    }

    /**
     * 功能描述: 根据角色ID查找对应的资源
     *
     * @param roleId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:36
     */
    @Override
    public List<RoleResourceDtoResp> findByRoleId(Integer roleId) throws Exception {
        QueryWrapper<RoleResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return DozerUtil.mapList(this.list(queryWrapper), RoleResourceDtoResp.class);
    }

    /**
     * 功能描述: 将新关联的资源加进去
     *
     * @param roleId
     * @param resourceId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:49
     */
    @Override
    public void addRoleResource(Integer roleId, Integer resourceId) throws Exception {
        RoleResourceEntity roleResourceEntity = new RoleResourceEntity();
        roleResourceEntity.setResourceId(resourceId);
        roleResourceEntity.setRoleId(roleId);
        this.save(roleResourceEntity);
    }
}
