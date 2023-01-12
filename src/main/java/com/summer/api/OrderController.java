package com.summer.api;

import com.summer.common.PageResult;
import com.summer.common.Response;
import com.summer.entity.request.ActivityJoinReq;
import com.summer.entity.request.ActivityPageReq;
import com.summer.entity.request.ActivityReq;
import com.summer.entity.request.OrderPageReq;
import com.summer.service.IActivityService;
import com.summer.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tubo
 * @date 2022/12/30
 */
@Api(value = "订单", tags = "订单")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "订单列表",response = Response.class)
    @PostMapping("/list")
    public Response list(@RequestBody OrderPageReq orderPageReq) throws IllegalAccessException {
        return Response.rspData(PageResult.success(orderService.orderPageList(orderPageReq)));
    }

}
