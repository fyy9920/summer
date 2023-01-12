package com.summer.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 首页表
 * @TableName t_home
 */
@TableName(value ="t_home")
@Data
public class Home implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    @TableId(type=IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 首页图片
     */
    private String bannerUrl;

    /**
     * 
     */
    private String detailUrl;

    /**
     * 
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createId;

    /**
     * 0未删除 1删除
     */
    @TableLogic
    private Integer deleteMark;


}