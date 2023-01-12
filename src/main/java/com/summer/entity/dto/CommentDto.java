package com.summer.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 *
 */
@Data
public class CommentDto implements Serializable {
    /**
     * 
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

    /**
     * 满意度 1-5
     */
    @ApiModelProperty(value = "满意度 1-5")
    @NotNull(message = "满意度不能为空")
    private Integer satisfaction;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 评论照片 多个照片用逗号隔开
     */
    @ApiModelProperty(value = "评论照片 多个照片用逗号隔开")
    private String imgUrl;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private Date createTime;



    private static final long serialVersionUID = 1L;
}