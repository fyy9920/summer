package com.summer.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评论
 *
 */
@Data
public class CommentReq implements Serializable {
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
     * 活动id
     */
    @ApiModelProperty(value = "活动id")
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    /**
     * 满意度 1-5
     */
    @ApiModelProperty(value = "满意度 1-5")
    @NotNull(message = "满意度不能为空")
    @Max(value = 5,message = "最大值不能大于5")
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


    private static final long serialVersionUID = 1L;
}