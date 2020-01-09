package com.baizhi.service;

import com.baizhi.vo.Map;

import java.util.List;

public interface UserService {
    //统计最近七天的注册量
    public List<Integer> queryUser();

    //统计12个月份的注册量
    public List<Integer> queryMonth();

    //地图
    public List<Map> selectMap();
}
