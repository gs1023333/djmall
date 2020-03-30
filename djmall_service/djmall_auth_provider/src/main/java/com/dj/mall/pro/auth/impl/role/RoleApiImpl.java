package com.dj.mall.pro.auth.impl.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.entity.auth.role.RoleEntity;
import com.dj.mall.mapper.auth.role.RoleMapper;
import com.dj.mall.model.base.SystemConstant;
import com.dj.mall.model.dto.auth.role.RoleDtoReq;
import com.dj.mall.model.dto.auth.role.RoleDtoResp;
import com.dj.mall.model.dto.auth.role.RoleResourceDtoResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.pro.auth.service.role.RoleResourceService;
import com.dj.mall.pro.auth.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:14
 */
@Service
public class RoleApiImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleApi {

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private UserRoleService userRoleService;
    @Reference
    private ResourceApi resourceApi;
    /**
     * 功能描述: 获取所有角色
     *
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 04:16
     */
    @Override
    public List<RoleDtoResp> getList() throws Exception {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", SystemConstant.IS_NOT_DEL);
        List<RoleEntity> roleEntityList = this.list(queryWrapper);
        return DozerUtil.mapList(roleEntityList, RoleDtoResp.class);
    }

    /**
     * 功能描述: 判断角色名是否存在
     *
     * @param roleName
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 12:49
     */
    @Override
    public RoleDtoResp findByName(String roleName) throws Exception {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        RoleEntity roleEntity = this.getOne(queryWrapper);
        if (roleEntity == null) {
            return null;
        }
        return  DozerUtil.map(roleEntity, RoleDtoResp.class);
    }

    /**
     * 功能描述: 新增角色
     *
     * @param roleDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 12:54
     */
    @Override
    public void addRole(RoleDtoReq roleDtoReq) throws Exception {
        this.save(DozerUtil.map(roleDtoReq, RoleEntity.class));
    }

    /**
     * 功能描述: 根据ID查找角色
     *
     * @param roleId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 12:59
     */
    @Override
    public RoleDtoResp findById(Integer roleId) throws Exception {
        RoleEntity roleEntity = this.getById(roleId);
        return DozerUtil.map(roleEntity, RoleDtoResp.class);
    }

    /**
     * 功能描述: 根据ID修改角色
     *
     * @param roleDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:05
     */
    @Override
    public void updateRoleById(RoleDtoReq roleDtoReq) throws Exception {
        this.updateById(DozerUtil.map(roleDtoReq, RoleEntity.class));
    }

    /**
     * 功能描述: 根据ID删除角色
     *
     * @param roleId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 01:11
     */
    @Override
    public void delRoleById(Integer roleId) throws Exception {
        roleResourceService.delRoleResource(roleId);
        userRoleService.delUserRole(roleId);
        this.removeById(roleId);
    }

    /**
     * 功能描述: 关联资源
     *
     * @param roleId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 04:15
     */
    @Override
    public void relatedResource(Integer roleId, Integer[] resourceIds) throws Exception {
        roleResourceService.delRoleResource(roleId);
        if (resourceIds != null) {
            for (Integer resourceId : resourceIds) {
                roleResourceService.addRoleResource(roleId, resourceId);
            }
        }
    }

    /**
     * 功能描述: 展示关联资源
     *
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 下午 04:18
     */
    @Override
    public List<RoleResourceDtoResp> showResource(Integer roleId) throws Exception {
        List<RoleResourceDtoResp> resourceDtoRespList = roleResourceService.findByRoleId(roleId);
        return resourceDtoRespList;
    }
}
