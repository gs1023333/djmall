package com.dj.mall.admin.web.auth.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.auth.resource.ResourceApi;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:08
 */
@Controller
@RequestMapping("/auth/resource")
public class ResourcePageController {
    @Reference
    private ResourceApi resourceApi;

    @RequestMapping("toShow")
    public String toShow() {
        return "/resource/show";
    }

    @RequestMapping("toAdd")
    public String toAdd(Integer parentId, Model model) throws Exception{
        if (parentId != 0) {
            ResourceDtoResp resourceDtoResp = resourceApi.getById(parentId);
            model.addAttribute("resource", resourceDtoResp);
        }
        model.addAttribute("parentId", parentId != null ? parentId : 0);
        return "/resource/add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(Integer resourceId, Model model) throws Exception{
        ResourceDtoResp resourceDtoResp = resourceApi.getById(resourceId);
        model.addAttribute("resource", resourceDtoResp);
        return "/resource/update";
    }
}
