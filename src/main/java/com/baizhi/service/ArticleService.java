package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {
    //分页查询
    public List<Article> queryPage(Integer page, Integer rows);

    //查询总条数
    public int selectTotalNumber();

    //批量删除
    public void delete(String[] id);

    //添加
    public void add(Article article);

    //根据id查询
    public Article queryById(String id);

    //修改
    public void amend(Article article);

    //查询所有
    public List<Article> queryAll();
}
