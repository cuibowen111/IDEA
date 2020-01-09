package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    //分页查询
    public List<Album> queryPage(Integer page, Integer rows);

    //查询总条数
    public int selectTotalNumber();

    //添加
    public void insertAlbum(Album album);

    //修改
    public void alter(Album album);

    //删除
    public void remove(String[] id);

    //查询所有
    public List<Album> queryAll();

    //根据id查询
    public Album queryById(String id);
}
