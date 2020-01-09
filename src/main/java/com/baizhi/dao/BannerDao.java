package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //查询所有轮播图
    public List<Banner> selectAll();

    //添加
    public void insert(Banner banner);

    //分页查询
    public List<Banner> selectPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public int bannerTotalNumber();

    //修改
    public void update(Banner banner);

    //批量删除
    public void delete(String[] id);
}
