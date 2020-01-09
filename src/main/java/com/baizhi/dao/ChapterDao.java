package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //查询所有
    public List<Chapter> selectAll();

    //分页查询
    public List<Chapter> selectPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("album_Id") String album_Id);

    //查询总条数
    public int chapterTotalNumber();

    //添加
    public void insert(Chapter chapter);

    //修改
    public void update(Chapter chapter);

    //删除
    public void delete(String[] id);

    //根据专辑id查询章节
    public List<Chapter> selectByAlbum_id(String id);
}
