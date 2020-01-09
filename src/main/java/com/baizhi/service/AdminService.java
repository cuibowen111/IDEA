package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.vo.Role;

import java.util.List;

public interface AdminService {
    //登录
    public Admin queryAll(String username);

    //查询所有
    public List<Admin> query();

    //根据用户查询角色
    public List<Role> selectRoleAll(String id);

    //根据角色查询权限
    public List<String> selectPermissionAll(String id);
}
