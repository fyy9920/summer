package com.summer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单表
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
public class Order implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 营员id，多个逗号隔开
     */
    private String camperId;

    /**
     * 种类
     */
    private String species;

    /**
     * 营期时间
     */
    private String entryTime;

    /**
     * 订单价格
     */
    private Integer prize;

    /**
     * 0未支付 1支付
     */
    private Integer status;

    /**
     * 0未发货 1已发货
     */
    private Integer deliveryStatus;

    /**
     * 收货地址
     */
    private Integer addressId;

    /**
     * 订单流水号
     */
    private String orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}