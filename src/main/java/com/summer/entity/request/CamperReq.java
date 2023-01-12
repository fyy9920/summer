package com.summer.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 营员
 */
@Data
public class CamperReq {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 营员名字
     */
    @ApiModelProperty(value = "营员名字")
    @NotBlank(message = "营员名字不能为空!")
    private String name;

    /**
     * 营员身份证号
     */
    @ApiModelProperty(value = "营员身份证号")
    @NotBlank(message = "营员身份证号不能为空!")
    private String idCard;

}