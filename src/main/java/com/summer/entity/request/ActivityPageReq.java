package com.summer.entity.request;

import com.summer.annotation.Query;
import com.summer.enums.QueryMethodEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author tubo
 * @date 2022/12/15
 */
@ApiModel("活动参数")
@Data
public class ActivityPageReq extends BasePageReq{

    @ApiModelProperty(value = "标题")
    @Query(attributeName = "title",type = QueryMethodEnum.LIKE)
    private String title;

    @ApiModelProperty(value = "类型 1 活动 2 官方好物",required = true)
    @Query(attributeName = "type",type = QueryMethodEnum.EQ)
    @NotNull(message = "类型不能为空")
    private Integer type;

}
