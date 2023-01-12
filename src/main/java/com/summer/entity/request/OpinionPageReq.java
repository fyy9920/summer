package com.summer.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 意见参数
 */
@ApiModel("意见分页参数")
@Data
public class OpinionPageReq extends BasePageReq {

    /**
     * 投诉意见内容
     */
    @ApiModelProperty(value = "content", required = true)
    private String content;


    /**
     * 创建人
     */
    @ApiModelProperty(value = "createBy",hidden = true)
    private Integer createBy;

}