package com.baizhi.shiro;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ApplicationContextUtil;
import com.baizhi.vo.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //主身份   这个就是用户名
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        /*
         * 1.根据主身份查询用户
         * 2.根据用户查询角色
         * 3.根据角色查询权限
         * */

        //根据主身份查询用户
        AdminService adminService = (AdminService) ApplicationContextUtil.getBean(AdminService.class);
        Admin admin = adminService.queryAll(primaryPrincipal);
        //根据用户查询角色
        List<Role> roles = adminService.selectRoleAll(admin.getId());
        //根据角色查询权限
        HashSet<String> hashSet = new HashSet<>();
        for (Role role : roles) {
            //自带缓存
            authorizationInfo.addRole(role.getName());
            List<String> strings = adminService.selectPermissionAll(role.getId());
            for (String string : strings) {
                hashSet.add(string);
            }
        }
        authorizationInfo.addStringPermissions(hashSet);
        return authorizationInfo;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 根据token 获得 身份信息  用户名
        String principal = (String) authenticationToken.getPrincipal();
        // service dao 导数据库查询
        // Admin admin = adminDao.queryByUsername(principal);
        AuthenticationInfo authenticationInfo = null;
        AdminService adminService = (AdminService) ApplicationContextUtil.getBean(AdminService.class);
        Admin admin = adminService.queryAll(principal);
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), this.getName());
        }
        return authenticationInfo;
    }
}
