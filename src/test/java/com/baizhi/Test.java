package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.ArticleDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Article;
import com.baizhi.vo.Role;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CmfzApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private UserDao userDao;

    @org.junit.Test
    public void test1() {
        articleDao.insert(new Article("33", "33", "33", "33", "33", new Date(), "33", "33"));
    }

    @org.junit.Test
    public void test2() throws IOException {
        //1.创建excle文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet sheet = workbook.createSheet("aaa");
        //3.行
        HSSFRow row = sheet.createRow(0);
        //4.单元格
        HSSFCell cell = row.createCell(0);
        //5.设值
        cell.setCellValue(new Date());
        //6.写出
        workbook.write(new File("E:/aaa.xls"));
        workbook.close();
    }

    @org.junit.Test
    public void test3() {
        Integer integer = userDao.selectOne();
        System.out.println(integer);
    }

    @org.junit.Test
    public void test4() {
        Integer querymonth = userDao.querymonth(1);
        System.out.println(querymonth);
    }

    @org.junit.Test
    public void test5() {
        System.out.println(adminDao);
        List<Role> roles = adminDao.selectRoleAll("1");
        System.out.println(roles);


    }

    @org.junit.Test
    public void test6() {
        List<String> permissions = adminDao.selectPermissionAll("1");
        for (String permission : permissions) {
            System.out.println(permission);
        }
    }

}
