package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("/queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows) {//分页查询
        /*
         * page     当前页
         * rows     数据
         * total    总页数
         * cont     总条数
         * */
        Integer start = (page - 1) * rows;//起始条数
        List<Article> articles = articleService.queryPage(start, rows);//数据
        int cont = articleService.selectTotalNumber();//总条数
        int total = articleService.selectTotalNumber() % rows == 0 ? articleService.selectTotalNumber() / rows : articleService.selectTotalNumber() / rows + 1;//总页数
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", articles);
        map.put("total", total);
        map.put("records", cont);
        return map;
    }

    @ResponseBody
    @RequestMapping("/edit")
    public Map<String, Object> edit(Article article, String oper, String[] id) {
        if ("add".equals(oper)) {
            article.setId(UUID.randomUUID().toString().replace("-", ""));
            article.setCreate_Date(new Date());
            articleService.add(article);
        } else if ("edit".equals(oper)) {
            articleService.amend(article);
        } else if ("del".equals(oper)) {
            articleService.delete(id);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/query")
    //根据id查询
    public Article query(String id) {
        Article article = articleService.queryById(id);
        return article;
    }

}
