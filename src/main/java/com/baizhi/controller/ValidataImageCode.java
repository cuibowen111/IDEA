package com.baizhi.controller;

import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/validataImageCode")
public class ValidataImageCode {

    @RequestMapping("/imageCode")
    public void getiImageCode(HttpServletResponse response, HttpSession session) {
        //验证码字符串
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("securityCode", securityCode);
        //字符图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        //将图片输出到页面
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
