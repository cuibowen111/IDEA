package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/banner")
public class BannerController {
    @Resource
    private BannerService bannerService;

    @ResponseBody
    @RequestMapping("/queryBanner")
    //查询所有轮播图
    public List<Banner> queryBanner() {
        List<Banner> banners = bannerService.queryAll();
        return banners;
    }

    @ResponseBody
    @RequestMapping("/edit")
    public Map<String, Object> edit(Banner banner, String oper, String[] id, HttpSession session) {
        if ("add".equals(oper)) {
            banner.setId(UUID.randomUUID().toString().replace("-", ""));
            banner.setCreate_Date(new Date());
            bannerService.insertBanner(banner);
            Map<String, Object> map = new HashMap<>();
            map.put("bannerId", banner.getId());
            return map;
        } else if ("edit".equals(oper)) {
            if (banner.getImg() == null || "".equals(banner.getImg())) {
                banner.setImg(null);
                bannerService.alter(banner);
                Map<String, Object> map = new HashMap<>();
                map.put("bannerId", null);
                return map;
            } else {
                banner.setImg(null);
                bannerService.alter(banner);
                //把原先的图片删除 要想实现必须根据id查询  根据id去删除原来图片
                /*String realPath = session.getServletContext().getRealPath("/img");
                File file = new File(realPath + "/" + banner.getImg());
                file.delete();*/
                Map<String, Object> map = new HashMap<>();
                map.put("bannerId", banner.getId());
                return map;
            }
        } else if ("del".equals(oper)) {
            bannerService.deleteArray(id);
            return null;
        }
        return null;
    }

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
        List<Banner> banners = bannerService.queryPage(start, rows);//数据
        int cont = bannerService.selectTotalNumber();//总条数
        int total = bannerService.selectTotalNumber() % rows == 0 ? bannerService.selectTotalNumber() / rows : bannerService.selectTotalNumber() / rows + 1;//总页数
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", banners);
        map.put("total", total);
        map.put("records", cont);
        return map;
    }

    @ResponseBody
    @RequestMapping("/upload")
    public void upload(MultipartFile img, HttpSession session, String bannerId) {
        //1. 获得 upload的路径
        String realPath = session.getServletContext().getRealPath("/upload");
        //2. 判断文件夹是否存在
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        //3.获取文件真实名字
        String originalFilename = img.getOriginalFilename();

        //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String name = new Date().getTime() + "_" + originalFilename;
        Banner banner = new Banner();
        banner.setImg(name);
        banner.setId(bannerId);
        bannerService.alter(banner);
        //5.文件上传
        try {
            img.transferTo(new File(realPath, name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
