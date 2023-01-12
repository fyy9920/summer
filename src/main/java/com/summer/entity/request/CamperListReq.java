package com.summer.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 营员列表参数
 */
@Data
public class CamperListReq {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer userId;

}