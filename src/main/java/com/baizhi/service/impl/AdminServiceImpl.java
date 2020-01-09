package com.baizhi.service.impl;

import com.baizhi.annotation.AddCache;
import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "adminSerivce")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public Admin queryAll(String username) {//登录
        Admin admin = adminDao.selectAll(username);
        return admin;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //查询所有
    public List<Admin> query() {
        List<Admin> select = adminDao.select();
        return select;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> selectRoleAll(String id) {
        return adminDao.selectRoleAll(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> selectPermissionAll(String id) {
        return adminDao.selectPermissionAll(id);
    }
}
