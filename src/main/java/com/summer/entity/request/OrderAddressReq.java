package com.summer.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 收货地址表
 * @TableName OrderAddressReq
 */
@Data
public class OrderAddressReq implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id 新增不用 修改必用")
    private Integer id;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    @NotBlank(message = "收货地址不能为空")
    private String address;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    @NotBlank(message = "省份不能为空")
    private String province;

    /**
     * 市区
     */
    @ApiModelProperty(value = "市区")
    @NotBlank(message = "市区不能为空")
    private String city;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    @NotBlank(message = "区域不能为空")
    private String district;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}