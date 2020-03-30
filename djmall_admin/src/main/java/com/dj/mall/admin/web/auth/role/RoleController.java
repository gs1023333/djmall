package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.resource.ResourceVoResp;
import com.dj.mall.admin.vo.auth.role.RoleResourceVoResp;
import com.dj.mall.admin.vo.auth.role.RoleVoReq;
import com.dj.mall.admin.vo.auth.role.RoleVoResp;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import com.dj.mall.model.dto.auth.role.RoleDtoReq;
import com.dj.mall.model.dto.auth.role.RoleDtoResp;
import com.dj.mall.model.dto.auth.role.RoleResourceDtoResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guoshuai
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:06
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController {


    @Reference
    private ResourceApi resourceApi;
    @Reference
    private RoleApi roleApi;


    @RequestMapping("show")
    public ResultModel<Object> show() throws Exception{
        Map<String, Object> map = new HashMap<>();
        List<RoleDtoResp> list = roleApi.getList();
        map.put("data", DozerUtil.mapList(list, RoleVoResp.class));
        return new ResultModel<>().success(map);
    }
    @RequestMapping("add")
    public ResultModel<Object> add(RoleVoReq roleVoReq) throws Exception{
        roleApi.addRole(DozerUtil.map(roleVoReq, RoleDtoReq.class));
        return new ResultModel<>().success(true);
    }
    @RequestMapping("findByName")
    public boolean findByName(String roleName) throws Exception{
        RoleDtoResp roleDtoResp = roleApi.findByName(roleName);
        return roleDtoResp == null ? true : false;
    }
    @RequestMapping("update")
    public ResultModel<Object> update(RoleVoReq roleVoReq) throws Exception{
        roleApi.updateRoleById(DozerUtil.map(roleVoReq, RoleDtoReq.class));
        return new ResultModel<>().success(true);
    }
    @RequestMapping("del")
    public ResultModel<Object> del(Integer roleId) throws Exception{
        roleApi.delRoleById(roleId);
        return new ResultModel<>().success(true);
    }
    @RequestMapping("showResource")
    public ResultModel<Object> showResource(Integer roleId) throws Exception{
        List<ResourceDtoResp> respList = resourceApi.queryList();
        List<ResourceVoResp> voRespList = DozerUtil.mapList(respList, ResourceVoResp.class);
        if (roleId != null) {
            List<RoleResourceDtoResp> resourceDtoRespList = roleApi.showResource(roleId);
            List<RoleResourceVoResp> resourceVoRespList = DozerUtil.mapList(resourceDtoRespList, RoleResourceVoResp.class);
            for (RoleResourceVoResp roleResourceVoResp : resourceVoRespList) {
                for (ResourceVoResp resourceVoResp : voRespList) {
                    if (resourceVoResp.getResourceId().equals(roleResourceVoResp.getResourceId())) {
                        resourceVoResp.setChecked(true);
                        break;
                    }
                }
            }
        }
        return new ResultModel<>().success(voRespList);
    }

    @RequestMapping("relatedResource")
    public ResultModel<Object> relatedResource(Integer roleId, @RequestParam(value = "resourceIds[]", required = false)Integer[] resourceIds) throws Exception{
        roleApi.relatedResource(roleId, resourceIds);
        return new ResultModel<>().success(true);
    }
}
