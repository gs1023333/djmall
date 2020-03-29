package com.dj.mall.admin.web.auth.role;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.api.auth.role.RoleApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:06
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController {
    @Reference
    private RoleApi roleApi;
}
