package com.baizhi.controller;

import com.baizhi.service.UserService;
import com.baizhi.vo.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/UserEcharts")
public class UserEchartsController {
    @Resource
    private UserService userService;

    //统计最近七天的注册量
    @ResponseBody
    @RequestMapping("/selelctUSerEcharts")
    public List<Integer> selelctUSerEcharts() {
        List<Integer> list = userService.queryUser();
        return list;
    }

    //统计最近12个月的注册量
    @ResponseBody
    @RequestMapping("/selelctMonthEcharts")
    public List<Integer> selelctMonthEcharts() {
        List<Integer> list = userService.queryMonth();
        return list;
    }

    //地图
    @ResponseBody
    @RequestMapping("/queryMap")
    public List<Map> queryMap() {
        List<Map> maps = userService.selectMap();
        return maps;
    }

}
