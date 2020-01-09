package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.vo.Map;

import java.util.List;

public interface UserDao {
    //根据主键删除
    int deleteByPrimaryKey(String id);

    //添加
    int insert(User record);

    //选择添加
    int insertSelective(User record);

    //根据主键去查询
    User selectByPrimaryKey(String id);

    //根据主键去选择的修改
    int updateByPrimaryKeySelective(User record);

    //根据主键去修改
    int updateByPrimaryKey(User record);

    //七天的注册量
    public Integer selectOne();

    public Integer selectTwo();

    public Integer selectThree();

    public Integer selectFour();

    public Integer selectFive();

    public Integer selectSix();

    public Integer selectSeven();

    //十二月的注册量
    public Integer querymonth(Integer id);

    //地图
    public List<Map> selectMap();
}