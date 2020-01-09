package com.baizhi.dao;

import com.baizhi.entity.Admin;
import com.baizhi.vo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    //管理员登录
    public Admin selectAll(String username);

    //查询所有
    public List<Admin> select();

    //授权
    public List<Role> selectRoleAll(@Param("id") String id);

    public List<String> selectPermissionAll(@Param("id") String id);
}
