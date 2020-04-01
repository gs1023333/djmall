package com.dj.mall.admin.config;

import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import com.dj.mall.model.dto.auth.user.UserDtoResp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-2-18 下午 08:00
 */
@Component
public class ShiroRelam extends AuthorizingRealm {


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 创建简单授权信息
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

        //获取session
        Session session = SecurityUtils.getSubject().getSession();
        UserDtoResp userDtoResp = (UserDtoResp) session.getAttribute("user");
        List<ResourceDtoResp> authList = userDtoResp.getAuthList();
        authList.forEach(auth -> simpleAuthorInfo.addStringPermission(auth.getResourceCode()));
        return simpleAuthorInfo;
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
        String userName = (String) authenticationToken.getPrincipal();
        //得到密码
        String password = new String((char[]) authenticationToken.getCredentials());
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
