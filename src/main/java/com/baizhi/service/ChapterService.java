package com.baizhi.service;

import com.baizhi.entity.Chapter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ChapterService {
    //分页查询
    public List<Chapter> queryPage(Integer page, Integer rows, String id);

    //查询总条数
    public int selectTotalNumber();

    //添加
    public void insertChapter(Chapter chapter);

    //修改
    public void alter(Chapter chapter);

    //批量删除
    public void delete(String[] id, String albumId);

    //文件下载
    public void downLoad(String src, HttpSession session, HttpServletResponse response);

    //根据专辑id查询章节
    public List<Chapter> queryByAlbum_id(String id);
}
