package com.baizhi.service.impl;

import com.baizhi.annotation.AddCache;
import com.baizhi.dao.UserDao;
import com.baizhi.service.UserService;
import com.baizhi.vo.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //统计最近七天的注册量
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public List<Integer> queryUser() {
        List<Integer> list = new ArrayList<>();
        list.add(userDao.selectOne());
        list.add(userDao.selectTwo());
        list.add(userDao.selectThree());
        list.add(userDao.selectFour());
        list.add(userDao.selectFive());
        list.add(userDao.selectSix());
        list.add(userDao.selectSeven());
        return list;
    }

    //统计12个月份的注册量
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public List<Integer> queryMonth() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++) {
            list.add(userDao.querymonth(i));
        }
        return list;
    }

    //地图
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public List<Map> selectMap() {
        List<Map> maps = userDao.selectMap();
        return maps;
    }
}
