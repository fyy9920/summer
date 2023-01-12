package com.summer.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    private String address;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 市区
     */
    @ApiModelProperty(value = "市区")
    private String city;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String district;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}