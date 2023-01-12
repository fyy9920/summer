package com.summer.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author tubo
 * @TableName ActivityReq
 */
@Data
public class ActivityReq implements Serializable {

    /**
     * 
     */
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;


    /**
     * 类型
     */
    @ApiModelProperty(value = "类型 1 活动 2官方好物")
    private Integer type;


    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Integer prize;


    /**
     * 类别
     */
    @ApiModelProperty(value = "类别")
    private Integer classify;

    /**
     * 一级页面图片
     */
    @ApiModelProperty(value = "一级页面图片")
    private String indexUrl;

    /**
     * 详情图片
     */
    @ApiModelProperty(value = "详情图片")
    private String detailUrl;

    /**
     * 参赛日期
     */
    @ApiModelProperty(value = "参赛日期 type=1时 活动有用")
    private List<String> entryTime;

    /**
     * 种类
     */
    @ApiModelProperty(value = "种类 type=1时 活动有用")
    private List<String> species;

    private static final long serialVersionUID = 1L;
}