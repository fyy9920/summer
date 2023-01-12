package com.summer.entity.request;

import com.summer.annotation.Query;
import com.summer.enums.QueryMethodEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 订单
 */
@Data
public class OrderPageReq extends BasePageReq implements Serializable {


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = true)
    @Query(attributeName = "userId",type= QueryMethodEnum.EQ)
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态 0 未付款 1 已付款", required = true)
    @Query(attributeName = "status",type= QueryMethodEnum.EQ)
    private Integer status;

    /**
     * 0 待发货 1已发货 2已收货
     */
    @ApiModelProperty(value = "0 待发货 1已发货 2已收货")
    @Query(attributeName = "deliveryStatus",type= QueryMethodEnum.EQ)
    private Integer deliveryStatus;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @Query(attributeName = "orderNum",type= QueryMethodEnum.EQ)
    private String orderNum;

    private static final long serialVersionUID = 1L;

}