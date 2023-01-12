package com.summer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_opinion
 */
@TableName(value ="t_opinion")
@Data
public class Opinion implements Serializable {
    /**
     * 
     */
    @TableId(type= IdType.AUTO)
    private Integer id;

    /**
     * 投诉意见内容
     */
    private String content;

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