package com.summer.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 意见参数
 */
@ApiModel("意见参数dto")
@Data
public class OpinionDto {

    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 投诉意见内容
     */
    @ApiModelProperty(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "createBy")
    private Integer createBy;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "createName")
    private String createName;

}