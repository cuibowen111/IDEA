package com.baizhi.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("kindeditor")
public class KindEditorController {

    @RequestMapping("uploadImg")
    public Map<String, Object> uploadImg(MultipartFile img, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = img.getOriginalFilename();
        String name = new Date().getTime() + "_" + originalFilename;
        try {
            img.transferTo(new File(realPath, name));
            /*
             *
             * {"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
             * {"error":0, "url":"http://localhost:80/cmfz/upload/img/xxx.png" }
             * */
            map.put("error", 0);
            String scheme = request.getScheme();                  //http

            InetAddress localHost = InetAddress.getLocalHost();  //localhost
            //PC-20190718ZLAM/192.168.1.156
            String localhost = localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();           //port
            String contextPath = request.getContextPath();      //项目名
            String url = scheme + "://" + localhost + ":" + serverPort + contextPath + "/upload/img/" + name;
            map.put("url", url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
    *
    * {
    "moveup_dir_path": "",
    "current_dir_path": "",
    "current_url": "/ke4/php/../attached/",
    "total_count": 5,
    "file_list": [
        {
            "is_dir": false,
            "has_file": false,
            "filesize": 208736,
            "dir_path": "",
            "is_photo": true,
            "filetype": "jpg",
            "filename": "1241601537255682809.jpg",
            "datetime": "2018-06-06 00:36:39"
        }
     ]
}
    * */
    @RequestMapping("getAllImgs")
    public Map<String, Object> getAllImgs(HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //1. 拿到所有 在图片空间展示的图片
        String realPath = session.getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        String[] imgs = file.list();
        List<Object> list = new ArrayList<>();
        for (String s : imgs) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("is_dir", false);
            hashMap.put("has_file", false);
            File file1 = new File(realPath, s);
            long length = file1.length();
            hashMap.put("filesize", length);
            hashMap.put("dir_path", "");
            hashMap.put("is_photo", true);
            //返回 资源名的  后缀
            String extension = FilenameUtils.getExtension(s);
            hashMap.put("filetype", extension);
            hashMap.put("filename", s);
            String str = s.split("_")[0];
            Long aLong = Long.valueOf(str);
            Date date = new Date(aLong);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            hashMap.put("datetime", format);
            list.add(hashMap);
        }
        map.put("file_list", list);
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        /*
         * http://localhost:80/cmfz/upload/img
         * */
        try {
            String scheme = request.getScheme();
            InetAddress localHost = InetAddress.getLocalHost();
            //PC-20190718ZLAM/192.168.1.156
            String lh = localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();
            //    /cmfz
            String contextPath = request.getContextPath();
            String current_url = scheme + "://" + lh + ":" + serverPort + contextPath + "/upload/img/";
            map.put("current_url", current_url);
            map.put("total_count", imgs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }
}
