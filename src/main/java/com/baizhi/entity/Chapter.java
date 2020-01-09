package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Chapter implements Serializable {

    private String id;
    private String title;
    private String album_Id;
    private String size;
    private String duration;
    private String src;
    private String status;
    private String other;

}
