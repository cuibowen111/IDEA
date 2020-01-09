package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.ChapterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/first_page")
public class First_Page {
    @Resource
    private BannerService bannerService;
    @Resource
    private AlbumService albumService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ChapterService chapterService;

    @ResponseBody
    @RequestMapping("/queryAll")
    public Map<String, Object> queryAll(String uid, String type) {
        if ("1".equals(uid)) {
            HashMap<String, Object> map = new HashMap<>();
            if ("all".equals(type)) {
                List<Banner> banners = bannerService.queryAll();
                map.put("header", banners);
                List<Album> albums = albumService.queryAll();
                map.put("album", albums);
                List<Article> articles = articleService.queryAll();
                map.put("artical", articles);
            } else if ("wen".equals(type)) {
                List<Album> albums = albumService.queryAll();
                map.put("album", albums);
            } else if ("si".equals(type)) {
                List<Article> articles = articleService.queryAll();
                map.put("artical", articles);
            }
            return map;
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/wen")
    public Map<String, Object> queryWen(String id, String uid) {
        if ("1".equals(uid)) {
            HashMap<String, Object> map = new HashMap<>();
            Album album = albumService.queryById(id);
            map.put("introduction", album);
            List<Chapter> chapters = chapterService.queryByAlbum_id(album.getId());
            map.put("list", chapters);
            return map;
        } else {
            return null;
        }
    }
}
