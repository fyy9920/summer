package com.summer.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页参数
 */
@ApiModel("首页参数")
@Data
public class HomeReq implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "首页图片链接")
    private String bannerUrl;


    @ApiModelProperty(value = "详情图片")
    private String detailUrl;


}