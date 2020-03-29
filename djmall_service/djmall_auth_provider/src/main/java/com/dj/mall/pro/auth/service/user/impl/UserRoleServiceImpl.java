package com.dj.mall.pro.auth.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.entity.auth.user.UserRoleEntity;
import com.dj.mall.mapper.auth.user.UserRoleMapper;
import com.dj.mall.pro.auth.service.user.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author guoshuai
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:26
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
}
