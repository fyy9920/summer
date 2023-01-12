package com.summer.entity.request;

import com.summer.annotation.Query;
import com.summer.enums.QueryMethodEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评论
 *
 */
@Data
public class CommentPageReq extends BasePageReq implements Serializable {


    /**
     * 活动id
     */
    @ApiModelProperty(value = "活动id",required = true)
    @NotNull(message = "活动id不能为空")
    @Query(attributeName ="activityId",type = QueryMethodEnum.EQ)
    private Integer activityId;


    private static final long serialVersionUID = 1L;
}