package com.baizhi.shiro;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroFilter {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        HashMap<String, String> map = new HashMap<>();
        //设置为匿名资源
        map.put("/admin/login", "anon");
        map.put("/validataImageCode/imageCode", "anon");
        map.put("/boot/**", "anon");
        map.put("/echarts/**", "anon");
        map.put("/img/**", "anon");
        map.put("/jqgrid/**", "anon");
        map.put("/kindeditor/**", "anon");
        map.put("/mp3/**", "anon");
        map.put("/upload/**", "anon");
        map.put("/jsp/assets/**", "anon");
        //所有的资源都是认证资源
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面的路径（shiro默认跳转的页面）
        shiroFilterFactoryBean.setLoginUrl("/jsp/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MyRealm myRealm, MemoryConstrainedCacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    //缓存Shiro
    @Bean
    public MemoryConstrainedCacheManager cacheManager() {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        return cacheManager;
    }
}
