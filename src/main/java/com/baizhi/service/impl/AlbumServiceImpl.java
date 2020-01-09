package com.baizhi.service.impl;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.ClearCache;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "albunmService")
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //分页查询
    public List<Album> queryPage(Integer page, Integer rows) {
        List<Album> albums = albumDao.selectPage(page, rows);
        return albums;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //查询总条数
    public int selectTotalNumber() {
        return albumDao.albumTotalNumber();
    }

    @Override
    @ClearCache
    //添加
    public void insertAlbum(Album album) {
        albumDao.insert(album);
    }

    @Override
    @ClearCache
    //修改
    public void alter(Album album) {
        albumDao.update(album);
    }

    @Override
    @ClearCache
    //删除
    public void remove(String[] id) {
        albumDao.delete(id);
    }

    //查询所有
    @Override
    @AddCache
    public List<Album> queryAll() {
        return albumDao.selectAll();
    }

    //根据id查询
    @AddCache
    @Override
    public Album queryById(String id) {
        return albumDao.selectById(id);
    }
}
