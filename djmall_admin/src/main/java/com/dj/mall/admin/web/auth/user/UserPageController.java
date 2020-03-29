package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.role.RoleVoResp;
import com.dj.mall.api.auth.role.RoleApi;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.model.dto.auth.role.RoleDtoResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.PasswordSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:32
 */
@Controller
@RequestMapping("/auth/user")
public class UserPageController {

    @Reference
    private RoleApi roleApi;
    @Reference
    private UserApi userApi;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "/user/login";
    }

    @RequestMapping("updateUserStatusByUserName")
    public String updateUserStatusByUserName(String userName) throws Exception{
        userApi.updateUserStatusByUserName(userName);
        return "/user/login";
    }

    @RequestMapping("toAdd")
    public String toAdd(Model model) {
        try {
            List<RoleDtoResp> list = roleApi.getList();
            model.addAttribute("list", DozerUtil.mapList(list, RoleVoResp.class));
            String salt = PasswordSecurityUtil.generateSalt();
            model.addAttribute("salt", salt);
            return "/user/add";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("toFindPwd")
    public String toFindPwd(Model model) throws Exception{
        String salt = PasswordSecurityUtil.generateSalt();
        model.addAttribute("salt", salt);
        return "/user/find_pwd";
    }
}
