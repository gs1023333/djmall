package com.dj.mall.admin.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 * @author guoshuai
 */
@Configuration
@DependsOn("shiroRelam")
public class ShiroConfiguration {

    /**
     * 自定义Realm
     */
    @Autowired
    private ShiroRelam shiroRelam;

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRelam);
        return securityManager;
    }

    /**
     * Shiro过滤器工厂
     *
     * @param securityManager
     * @return
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        // 定义 shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置默认登录的 URL，身份认证失败会访问该 URL
        shiroFilterFactoryBean.setLoginUrl("/auth/user/toLogin");
        // 设置成功之后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index/index");
        // 设置未授权界面，权限认证失败会访问该 URL
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.jsp");
        Map<String, String> filters = new LinkedHashMap<>();
        // anon 表示不需要认证

        filters.put("/static/**", "anon");
        filters.put("/auth/user/login", "anon");
        filters.put("/auth/user/getSalt", "anon");
        filters.put("/auth/user/toAdd", "anon");
        filters.put("/auth/user/add", "anon");
        filters.put("/auth/user/difference", "anon");
        filters.put("/auth/user/findByName", "anon");
        filters.put("/auth/user/findByEmail", "anon");
        filters.put("/auth/user/findByPhone", "anon");
        filters.put("/auth/user/update", "anon");
        filters.put("/auth/user/updateUserStatusByUserName", "anon");
        filters.put("/auth/user/toFindPwd", "anon");
        filters.put("/auth/user/findOneByPhone", "anon");
        filters.put("/auth/user/findByPhoneAndCode", "anon");
        filters.put("/auth/user/verify", "anon");
        filters.put("/auth/user/findPwd", "anon");
        filters.put("/auth/user/toUpdatePwd", "anon");

        // authc 表示必须认证才可访问
        filters.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }

}
