package com.baizhi.service.impl;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.ClearCache;
import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Transactional
@Service(value = "chapterService")
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //分页查询
    public List<Chapter> queryPage(Integer page, Integer rows, String id) {
        return chapterDao.selectPage(page, rows, id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    //查询总条数
    public int selectTotalNumber() {
        return chapterDao.chapterTotalNumber();
    }

    @Override
    @ClearCache
    //添加
    public void insertChapter(Chapter chapter) {
        chapterDao.insert(chapter);
        albumDao.increase(chapter.getAlbum_Id());
    }

    @Override
    @ClearCache
    //修改
    public void alter(Chapter chapter) {
        chapterDao.update(chapter);
    }

    @Override
    @ClearCache
    //删除
    public void delete(String[] id, String albumId) {
        chapterDao.delete(id);
        albumDao.reduce(albumId, id.length);
    }

    @Override
    public void downLoad(String src, HttpSession session, HttpServletResponse response) {
        String realPath = session.getServletContext().getRealPath("/mp3");
        File file = new File(realPath, src);
        String name = src.split("_")[1];

        //编码格式
        try {
            String encode = URLEncoder.encode(name, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //附件形式下载    设置响应头
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            FileUtils.copyFile(file, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //根据专辑id查询章节
    @AddCache
    public List<Chapter> queryByAlbum_id(String id) {
        return chapterDao.selectByAlbum_id(id);
    }
}
