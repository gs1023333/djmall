package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.auth.role.RoleApi;
import org.springframework.stereotype.Controller;
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
}
