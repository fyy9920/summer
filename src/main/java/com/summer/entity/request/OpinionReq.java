package com.summer.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 意见参数
 */
@ApiModel("意见参数")
@Data
public class OpinionReq implements Serializable {

    /**
     * 投诉意见内容
     */
    @ApiModelProperty(value = "content", required = true)
    @NotBlank(message = "投诉意见内容不能为空")
    private String content;


    /**
     * 创建人
     */
    @ApiModelProperty(value = "createBy",hidden = true)
    private Integer createBy;

}