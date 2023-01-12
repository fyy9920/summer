package com.summer.entity.request;

import com.summer.annotation.Query;
import com.summer.enums.QueryMethodEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author tubo
 * @date 2022/12/15
 */
@Data
public class BasePageReq {

    @ApiModelProperty(value = "当前页", name = "pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "当前页显示条数", name = "pageSize")
    private Integer pageSize = 10;

/*    @ApiModelProperty(value = "排序字段", name = "sort")
    @Query(type = QueryMethodEnum.SORT)
    private String sort;

    @ApiModelProperty(value = "排序方式  asc-正序 desc-倒序", name = "order")
    @Query(type = QueryMethodEnum.ORDER)
    private String order;*/

}
