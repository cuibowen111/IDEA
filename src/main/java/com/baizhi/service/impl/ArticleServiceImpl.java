package com.baizhi.service.impl;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.ClearCache;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    //分页
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public List<Article> queryPage(Integer page, Integer rows) {
        List<Article> articles = articleDao.selectPage(page, rows);
        return articles;
    }

    @Override
    //总条数
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public int selectTotalNumber() {
        return articleDao.articleTotalNumber();
    }

    @Override
    @ClearCache
    //删除
    public void delete(String[] id) {
        articleDao.delete(id);
    }

    @Override
    @ClearCache
    //添加
    public void add(Article article) {
        articleDao.insert(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //根据id查询
    public Article queryById(String id) {
        Article article = articleDao.selectById(id);
        return article;
    }

    //修改
    @Override
    @ClearCache
    public void amend(Article article) {
        articleDao.update(article);
    }

    //查询所有
    @Override
    @AddCache
    public List<Article> queryAll() {
        return articleDao.selectAll();
    }
}
