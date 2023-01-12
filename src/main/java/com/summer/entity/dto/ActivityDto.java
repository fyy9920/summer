package com.summer.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.summer.entity.WechatUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author tubo
 * @TableName t_activity
 */
@Data
@ApiModel("活动响应数据")
public class ActivityDto implements Serializable {

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
     * 围观人数
     */
    @ApiModelProperty(value = "围观人数",hidden = true)
    private Integer onlookers;

    /**
     * 围观人
     */
    @ApiModelProperty(value = "围观人",hidden = true)
    private List<WechatUser> wechatUserList;

    /**
     * 最近一期
     */
    @ApiModelProperty(value = "最近一期",hidden = true)
    private String lastDate;

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

    @ApiModelProperty(value = "种类 ",required = true)
    private List<String> species;

    /**
     * 活动时间
     */
    @ApiModelProperty(value = "活动时间")
    private List<String> entryTime;

    /**
     *
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    /**
     *
     */
    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private Integer createBy;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人",hidden = true)
    private Integer updateBy;

    private static final long serialVersionUID = 1L;
}