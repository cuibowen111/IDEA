package com.baizhi.service.impl;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.ClearCache;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "bannerService")
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //查询所有
    public List<Banner> queryAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }

    @Override
    @ClearCache
    //添加
    public void insertBanner(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //分页查询
    public List<Banner> queryPage(Integer page, Integer rows) {
        return bannerDao.selectPage(page, rows);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //查询总条数
    public int selectTotalNumber() {
        return bannerDao.bannerTotalNumber();
    }

    @Override
    @ClearCache
    //修改
    public void alter(Banner banner) {
        bannerDao.update(banner);
    }

    @Override
    @ClearCache
    //批量删除
    public void deleteArray(String[] id) {
        bannerDao.delete(id);
    }
}
