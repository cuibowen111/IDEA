package com.baizhi;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.service.AdminService;
import com.baizhi.vo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private AdminService adminService;

    @Test
    public void contextLoads() {
        Admin lisi = adminDao.selectAll("lisi");
        System.out.println(lisi);
    }

    @Test
    public void test1() {
        Admin lisi = adminService.queryAll("lisi");
        System.out.println(lisi);
    }

    @Test
    public void test2() {
        List<Banner> banners = bannerDao.selectAll();
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }

    @Test
    public void test3() {
        bannerDao.insert(new Banner("2", "1", "1", new Date(), "1"));
    }

    @Test
    public void test4() {
        bannerDao.update(new Banner("a4e123e0351c4ca6bf4f8f27d1519040", "1", "2", new Date(), "11"));
    }

    @Test
    public void test5() {
        List<Album> albums = albumDao.selectAll();
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    @Test
    public void test6() {
        List<Chapter> chapters = chapterDao.selectAll();
        for (Chapter chapter : chapters) {
            System.out.println(chapter);
        }
    }

    @Test
    public void test7() {
        chapterDao.insert(new Chapter("3", "1", "1", "1", "1", "1", "1", "1"));
    }

    @Test
    public void test8() {
        albumDao.insert(new Album("2", "2", "2", "2", "2", "2", 1, "2", new Date(), "2", "2"));
    }

    @Test
    public void test9() {
        List<Article> articles = articleDao.selectPage(0, 1);
        System.out.println(articles);
    }

    @Test
    public void test10() {
        List<Role> roles = adminDao.selectRoleAll("1");
        System.out.println(roles);
    }

}
