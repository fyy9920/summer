package com.summer.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 活动id
     */
    private Integer activityId;

    /**
     * 满意度 1-5
     */
    private Integer satisfaction;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论照片 多个照片用逗号隔开
     */
    private String imgUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 0未删除 1删除
     */
    @TableLogic
    private Integer deleteMark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}