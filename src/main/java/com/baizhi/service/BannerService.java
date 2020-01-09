package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    //查询所有
    public List<Banner> queryAll();

    //添加
    public void insertBanner(Banner banner);

    //分页查询
    public List<Banner> queryPage(Integer page, Integer rows);

    //查询总条数
    public int selectTotalNumber();

    //修改
    public void alter(Banner banner);

    //批量删除
    public void deleteArray(String[] id);
}
