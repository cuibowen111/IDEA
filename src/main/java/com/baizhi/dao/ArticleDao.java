package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    //分页查询
    public List<Article> selectPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询总条数
    public int articleTotalNumber();

    //删除
    public void delete(String[] id);

    //添加
    public void insert(Article article);

    //根据id查询
    public Article selectById(String id);

    //修改
    public void update(Article article);

    //查询所有
    public List<Article> selectAll();
}
