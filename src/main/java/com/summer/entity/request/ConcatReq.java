package com.summer.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 合作联系
 */
@Data
@ApiModel("合作联系参数")
public class ConcatReq {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 咨询电话
     */
    @ApiModelProperty(value = "id")
    private String phone;

}