package com.dj.mall.pro.auth.impl.role;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.entity.auth.role.RoleEntity;
import com.dj.mall.mapper.auth.role.RoleMapper;
import com.dj.mall.model.base.SystemConstant;
import com.dj.mall.model.dto.auth.role.RoleDtoResp;
import com.dj.mall.model.util.DozerUtil;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:14
 */
@Service
public class RoleApiImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleApi {

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
}
