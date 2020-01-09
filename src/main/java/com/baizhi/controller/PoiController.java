package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/poi")
public class PoiController {
    @Resource
    private AdminService adminService;

    @Resource
    private BannerService bannerService;

    @RequestMapping("/poiExport")
    public void exportAll(HttpServletResponse response) {
        List<Admin> query = adminService.query();

        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("管理员");
        //设置长宽
        sheet.setColumnWidth(2, 4500);
        //创建导出样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //创建字体
        HSSFFont font = workbook.createFont();
        //设置字体颜色
        font.setColor(HSSFFont.COLOR_RED);
        //设置加粗
        font.setBold(true);
        //设置字体
        font.setFontName("黑体");
        //设置居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //管理字体样式
        cellStyle.setFont(font);
        //创建标题栏
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        String[] titles = {"编号", "用户名", "密码"};
        for (int i = 0; i < titles.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            //标题行使用样式
            cell.setCellStyle(cellStyle);
        }
        for (int i = 1; i <= query.size(); i++) {
            //创建数据行对象
            row = sheet.createRow(i);
            //数据行第一列设值
            cell = row.createCell(0);
            cell.setCellValue(query.get(i - 1).getId());
            //数据行第二列设值
            cell = row.createCell(1);
            cell.setCellValue(query.get(i - 1).getUsername());
            //数据行第三列设值
            cell = row.createCell(2);
            cell.setCellValue(query.get(i - 1).getPassword());
        }
        response.setHeader("content-disposition", "attachment;filename=admin.xls");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("bannerEasypoi")
    public void bannerEasypoi(HttpServletResponse response) {
        List<Banner> banners = bannerService.queryAll();
        for (Banner banner : banners) {
            //图片路径
            banner.setImg("F:\\IDEA\\cmfz_cbw\\src\\main\\webapp\\upload\\" + banner.getImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图", "轮播图"), Banner.class, banners);
        response.setHeader("content-disposition", "attachment;filename=banner.xls");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
