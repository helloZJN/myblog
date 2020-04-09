package com.zjn.myblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @TableId(type = IdType.AUTO)
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //图片地址
    private String firstPicture;
    //标记
    private String flag;
    //浏览次数
    private Integer views;
    //是否发布
    private boolean published;

    private Date createTime;
    private Date updateTime;

    private Long typeId;
    private String typeName;

    private Long userId;

}
