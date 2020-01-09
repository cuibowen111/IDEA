package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查询所有
    public List<Album> selectAll();

    //分页查询
    public List<Album> selectPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public int albumTotalNumber();

    //添加
    public void insert(Album album);

    //修改
    public void update(Album album);

    //删除
    public void delete(String[] id);

    //专辑数增加
    public void increase(@Param("id") String id);

    //专辑数减少
    public void reduce(@Param("id") String id, @Param("count") int count);

    //根据id查询
    public Album selectById(@Param("id") String id);
}
