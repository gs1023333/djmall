package com.dj.mall.pro.auth.impl.resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.mapper.auth.resource.ResourceMapper;
import com.dj.mall.model.base.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDtoReq;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import com.dj.mall.model.util.DozerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:01
 */
@Service
public class ResourceApiImpl extends ServiceImpl<ResourceMapper, ResourceEntity> implements ResourceApi {

    /**
     * 功能描述: 展示资源
     *
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 09:46
     */
    @Override
    public List<ResourceDtoResp> queryList() throws Exception {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", SystemConstant.IS_NOT_DEL);
        List<ResourceEntity> list = this.list(queryWrapper);
        List<ResourceDtoResp> resourceDtoRespList = DozerUtil.mapList(list, ResourceDtoResp.class);
        for (ResourceDtoResp resourceDtoResp : resourceDtoRespList) {
            resourceDtoResp.setOpen(true);
        }
        return resourceDtoRespList;
    }

    /**
     * 功能描述: 根据ID查资源
     *
     * @param id
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 09:56
     */
    @Override
    public ResourceDtoResp getById(Integer id) throws Exception {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        ResourceEntity resourceEntity = this.getOne(queryWrapper);
        return DozerUtil.map(resourceEntity, ResourceDtoResp.class);
    }

    /**
     * 功能描述: 根据父集ID查找用户
     *
     * @param parentId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 上午 12:01
     */
    @Override
    public ResourceDtoResp getByParentId(Integer parentId) throws Exception {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        ResourceEntity resourceEntity = this.getOne(queryWrapper);
        return DozerUtil.map(resourceEntity, ResourceDtoResp.class);
    }

    /**
     * 功能描述: 新增资源
     *
     * @param resourceDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 10:03
     */
    @Override
    public void addResource(ResourceDtoReq resourceDtoReq) throws Exception {
        String upperCase = resourceDtoReq.getResourceCode().toUpperCase();
        resourceDtoReq.setResourceCode(upperCase);
        this.save(DozerUtil.map(resourceDtoReq, ResourceEntity.class));
    }

    /**
     * 功能描述: 根据ID修改资源
     *
     * @param resourceDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 10:13
     */
    @Override
    public void updateResourceById(ResourceDtoReq resourceDtoReq) throws Exception {
        String upperCase = resourceDtoReq.getResourceCode().toUpperCase();
        resourceDtoReq.setResourceCode(upperCase);
        this.updateById(DozerUtil.map(resourceDtoReq, ResourceEntity.class));
    }

    /**
     * 功能描述: 根据ID删除资源
     *
     * @param resourceId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 10:16
     */
    @Override
    public void delResourceById(Integer resourceId) throws Exception {
        List<Integer> ids = new ArrayList<>();
        getIds(resourceId , ids);
        this.removeByIds(ids);
    }

    /**
     * 递归
     */
    private void getIds(Integer resourceId, List<Integer> ids) {
        QueryWrapper<ResourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", resourceId);
        List<ResourceEntity> list = this.list(queryWrapper);
        ids.add(resourceId);
        if (list != null && list.size() > 0) {
            for (ResourceEntity resource : list) {
                getIds(resource.getId(), ids);
            }
        }
    }
}
