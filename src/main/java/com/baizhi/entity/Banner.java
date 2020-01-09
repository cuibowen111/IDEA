package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Banner implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "标签")
    private String title;
    @Excel(name = "图片", type = 2, height = 20, width = 50)
    private String img;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "创建时间", format = "yyyy-MM-dd")
    private Date create_Date;
    @Excel(name = "状态")
    private String status;

}
