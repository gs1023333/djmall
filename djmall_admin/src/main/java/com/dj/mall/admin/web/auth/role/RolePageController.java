package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.role.RoleVoResp;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.model.dto.auth.role.RoleDtoResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:07
 */
@Controller
@RequestMapping("/auth/role")
public class RolePageController {
    @Reference
    private RoleApi roleApi;

    @RequestMapping("toShow")
    public String toShow() {
        return "/role/show";
    }
    @RequestMapping("toAdd")
    public String toAdd() {
        return "/role/add";
    }
    @RequestMapping("toUpdate")
    public String toUpdate(Integer roleId, Model model) throws Exception{
        RoleDtoResp roleDtoResp = roleApi.findById(roleId);
        model.addAttribute("r", DozerUtil.map(roleDtoResp, RoleVoResp.class));
        return "/role/update";
    }
    @RequestMapping("toShowResource")
    public String toShowResource(Integer roleId, Model model) throws Exception{
        model.addAttribute("roleId", roleId);
        return "/role/resource";
    }
}
