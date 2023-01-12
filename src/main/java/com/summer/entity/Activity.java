package com.summer.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author tubo
 * @TableName t_activity
 */
@TableName(value ="t_activity")
@Data
public class Activity implements Serializable {

    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 围观人数
     */
    private Integer onlookers;

    /**
     * 一级页面图片
     */
    private String indexUrl;

    /**
     * 详情图片
     */
    private String detailUrl;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改人
     */
    private Integer updateBy;


    /**
     * 类型 1 活动  2官方好物
     */
    private Integer type;


    /**
     * 价格
     */
    private Integer prize;


    /**
     * 类别
     */
    private Integer classify;

    /**
     * 种类
     */
    private String species;

    /**
     * 活动时间
     */
    private String entryTime;

    /**
     * 0未删除 1删除
     */
    @TableLogic
    private Integer deleteMark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}