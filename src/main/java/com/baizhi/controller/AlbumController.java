package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
@RequestMapping("/album")
public class AlbumController {
    @Resource
    private AlbumService albumService;

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
        List<Album> albums = albumService.queryPage(start, rows);//数据
        int cont = albumService.selectTotalNumber();//总条数
        int total = albumService.selectTotalNumber() % rows == 0 ? albumService.selectTotalNumber() / rows : albumService.selectTotalNumber() / rows + 1;//总页数
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", albums);
        map.put("total", total);
        map.put("records", cont);
        return map;
    }

    //添加
    @ResponseBody
    @RequestMapping("/edit")
    public Map<String, Object> edit(Album album, String oper, String[] id) {
        if ("add".equals(oper)) {
            album.setId(UUID.randomUUID().toString().replace("-", ""));
            albumService.insertAlbum(album);
            Map<String, Object> map = new HashMap<>();
            map.put("albumId", album.getId());
            return map;
        } else if ("edit".equals(oper)) {
            if ("".equals(album.getImg()) || album.getImg() == null) {
                album.setId(null);
                albumService.insertAlbum(album);
                Map<String, Object> map = new HashMap<>();
                map.put("chapterId", null);
                return map;
            } else {
                albumService.insertAlbum(album);
                Map<String, Object> map = new HashMap<>();
                map.put("chapterId", null);
                return map;
            }
        } else if ("del".equals(oper)) {
            albumService.remove(id);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/upload")
    public void upload(MultipartFile img, HttpSession session, String albumId) {
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
        //5.文件上传
        try {
            img.transferTo(new File(realPath, name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Album album = new Album();
        album.setImg(name);
        album.setId(albumId);
        albumService.alter(album);
    }


}
