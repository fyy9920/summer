package com.summer.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tubo
 * @date 2022/12/15
 */
@ApiModel("参加活动参数")
@Data
public class ActivityJoinReq {

    @ApiModelProperty(value = "活动id",required = true)
    @NotNull(message = "活动id不能为空")
    private Integer activityId;

    /*@ApiModelProperty(value = "用户id",required = true)
    private Integer userId;*/

    @ApiModelProperty(value = "营员id",required = true)
    private List<String> camperId;

    @ApiModelProperty(value = "价格",required = true)
    @NotNull(message = "价格不能为空")
    private Integer prize;

    @ApiModelProperty(value = "种类  如：单板  双板",required = true)
    private String species;

    @ApiModelProperty(value = "营期时间",required = true)
    private String entryTime;


    @ApiModelProperty(value = "收货地址",required = true)
    @NotNull(message = "收货地址不能为空")
    private Integer addressId;

    @ApiModelProperty(value = "1 活动结算  2 官方好物结算",required = true)
    private Integer type;

}
