package com.summer.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 * @TableName t_order
 */
@Data
public class OrderDto implements Serializable {



    @ApiModelProperty(value = "content")
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;


    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "商品图片")
    private String activityImg;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;


    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 营员id，多个逗号隔开
     */
    @ApiModelProperty(value = "营员id，多个逗号隔开")
    private String camperId;


    @ApiModelProperty(value = "营员名称")
    private List<String> camperName;


    /**
     * 种类
     */
    @ApiModelProperty(value = "种类")
    private String species;

    /**
     * 营期时间
     */
    @ApiModelProperty(value = "营期时间")
    private String entryTime;

    /**
     * 订单价格
     */
    @ApiModelProperty(value = "订单价格")
    private Integer prize;

    /**
     * 0未支付 1支付
     */
    @ApiModelProperty(value = "0未支付 1支付")
    private Integer status;

    /**
     * 未支付 支付
     */
    @ApiModelProperty(value = "未支付 支付")
    private String payStatusName;

    /**
     * 0未发货 1已发货
     */
    @ApiModelProperty(value = "0未发货 1已发货")
    private Integer deliveryStatus;

    /**
     * 未发货 已发货
     */
    @ApiModelProperty(value = "未发货 已发货")
    private String deliveryStatusName;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String address;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 市区
     */
    @ApiModelProperty(value = "市区")
    private String city;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String district;


    /**
     * 订单流水号
     */
    @ApiModelProperty(value = "订单流水号")
    private String orderNum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}