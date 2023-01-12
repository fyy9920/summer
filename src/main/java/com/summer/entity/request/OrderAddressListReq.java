package com.summer.entity.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 收货地址列表参数
 * @TableName OrderAddressReq
 */
@Data
public class OrderAddressListReq implements Serializable {

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}