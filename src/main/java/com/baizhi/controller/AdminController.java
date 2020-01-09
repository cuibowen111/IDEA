package com.baizhi.controller;

import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ResponseBody
    @RequestMapping("/login")
    //登录
    public String login(String username, String password, String enCode, HttpSession session) {
        String name;
        if (enCode.equals(session.getAttribute("securityCode"))) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return name = "账号有误";
            } catch (IncorrectCredentialsException e) {
                return name = "密码有误";
            }
            return name = "aaa";
            /*Admin admin = adminService.queryAll(username);
            if (admin==null){
                return name="用户不存在";
            } else {
                if (admin.getPassword().equals(password)){
                    session.setAttribute("admin",admin);
                    return name="aaa";
                }else {
                    return name="用户密码错误";
                }
            }*/
        } else {
            return name = "验证码错误";
        }
    }

    @RequestMapping("/logOut")
    //退出
    public void logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
