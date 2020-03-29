package com.dj.mall.admin.web.auth.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.resource.ResourceVoReq;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.auth.resource.ResourceDtoReq;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:06
 */
@RestController
@RequestMapping("/auth/resource")
public class ResourceController {
    @Reference
    private ResourceApi resourceApi;

    @RequestMapping("show")
    public ResultModel<Object> show() throws Exception{
        List<ResourceDtoResp> resourceDtoRespList = resourceApi.queryList();
        return new ResultModel<>().success(resourceDtoRespList);
    }
    @RequestMapping("add")
    public ResultModel<Object> add(ResourceVoReq resourceVoReq) throws Exception{
        resourceApi.addResource(DozerUtil.map(resourceVoReq, ResourceDtoReq.class));
        return new ResultModel<>().success(true);
    }
    @RequestMapping("update")
    public ResultModel<Object> update(ResourceVoReq resourceVoReq) throws Exception{
        resourceApi.updateResourceById(DozerUtil.map(resourceVoReq, ResourceDtoReq.class));
        return new ResultModel<>().success(true);
    }
    @RequestMapping("del")
    public ResultModel<Object> del(Integer resourceId) throws Exception{
        resourceApi.delResourceById(resourceId);
        return new ResultModel<>().success(true);
    }

}
