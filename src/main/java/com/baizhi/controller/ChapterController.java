package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.VideoTimeUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @ResponseBody
    @RequestMapping("/queryByPage")
    public HashMap<String, Object> queryByPage(Integer page, Integer rows, String id) {//分页查询
        /*
         * page     当前页
         * rows     数据
         * total    总页数
         * cont     总条数
         * */
        Integer start = (page - 1) * rows;//起始条数
        List<Chapter> chapters = chapterService.queryPage(start, rows, id);//数据
        int cont = chapterService.selectTotalNumber();//总条数
        int total = chapterService.selectTotalNumber() % rows == 0 ? chapterService.selectTotalNumber() / rows : chapterService.selectTotalNumber() / rows + 1;//总页数
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("rows", chapters);
        map.put("total", total);
        map.put("records", cont);
        return map;
    }

    //添加
    @ResponseBody
    @RequestMapping("/edit")
    public Map<String, Object> edit(Chapter chapter, String oper, HttpSession session, String albumId, String[] id) {
        if ("add".equals(oper)) {
            chapter.setId(UUID.randomUUID().toString().replace("-", ""));
            chapter.setAlbum_Id(albumId);
            chapterService.insertChapter(chapter);
            Map<String, Object> map = new HashMap<>();
            map.put("chapterId", chapter.getId());
            return map;
        } else if ("edit".equals(oper)) {
            if ("".equals(chapter.getSrc()) || chapter.getSrc() == null) {
                chapter.setSrc(null);
                chapterService.alter(chapter);
                Map<String, Object> map = new HashMap<>();
                map.put("chapterId", null);
                return map;
            } else {
                chapterService.alter(chapter);
                Map<String, Object> map = new HashMap<>();
                map.put("chapterId", chapter.getId());
                return map;
            }
        } else if ("del".equals(oper)) {
            chapterService.delete(id, albumId);
            return null;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/upload")
    //上传
    public void upload(MultipartFile src, HttpSession session, String chapterId) {
        //1. 获得 绝对的路径
        String realPath = session.getServletContext().getRealPath("/mp3");
        //2. 判断文件夹是否存在
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        //3.获取文件真实名字
        String originalFilename = src.getOriginalFilename();

        //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String name = new Date().getTime() + "_" + originalFilename;
        //5.文件上传
        try {
            src.transferTo(new File(realPath, name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Chapter chapter = new Chapter();
        chapter.setId(chapterId);
        chapter.setSrc(name);
        // 获取音频时长
        File file = new File(realPath + "/" + name);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo info = encoder.getInfo(file);
            long duration = info.getDuration() / 1000;
            String s = VideoTimeUtil.formatTime(duration);
            chapter.setDuration(s);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        //获取音频大小
        long length = file.length();
        String printSize = VideoTimeUtil.getPrintSize(length);
        chapter.setSize(printSize);
        chapterService.alter(chapter);
    }

    @RequestMapping("/downLoad")
    //下载
    public void downLoad(String src, HttpSession session, HttpServletResponse response) {
        chapterService.downLoad(src, session, response);
    }
}
