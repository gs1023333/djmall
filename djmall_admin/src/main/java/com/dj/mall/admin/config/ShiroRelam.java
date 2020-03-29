package com.dj.mall.admin.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.user.UserVoResp;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.model.dto.auth.user.UserDtoReq;
import com.dj.mall.model.dto.auth.user.UserDtoResp;
import com.dj.mall.model.util.DozerUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-2-18 下午 08:00
 */
@Component
public class ShiroRelam extends AuthorizingRealm {

    @Reference
    private UserApi userApi;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       /* // 创建简单授权信息
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("user");
        QueryWrapper<RoleResource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.eq("role_id", user.getLevel());
        List<RoleResource> roleResourceList = roleResourceService.list(resourceQueryWrapper);
        List<Resource> list = new ArrayList<>();
        for (RoleResource roleResource : roleResourceList) {
            Resource resource = resourceService.getById(roleResource.getResourceId());
            if (!resource.getParentId().equals(SystemConstant.IS_DEL_0)) {
                list.add(resource);
            }
        }
        for (Resource resource : list) {
            simpleAuthorInfo.addStringPermission(resource.getUrl());
        }*/
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //得到用户名
        try {
            String userName = (String) authenticationToken.getPrincipal();
            //得到密码
            String password = new String((char[]) authenticationToken.getCredentials());
            //获取session
            Session session = SecurityUtils.getSubject().getSession();
            UserDtoResp userDtoResp = userApi.getByUserName(userName, password);
            UserVoResp userVoResp = DozerUtil.map(userDtoResp, UserVoResp.class);
            userVoResp.setEndTime(new Date());
            userApi.updateUserByUserId(DozerUtil.map(userVoResp, UserDtoReq.class));
            session.setAttribute("user", userVoResp);
            return new SimpleAuthenticationInfo(userName, password, getName());
        } catch (Exception e) {
            throw new AccountException(e.getMessage());
        }
    }
}
