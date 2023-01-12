package com.summer.api;

import com.summer.common.PageResult;
import com.summer.common.Response;
import com.summer.entity.OrderAddress;
import com.summer.entity.request.OrderAddressReq;
import com.summer.entity.request.OrderPageReq;
import com.summer.service.OrderAddressService;
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
@Api(value = "收货地址", tags = "收货地址")
@RestController
@RequestMapping("/orderAddress")
public class OrderAddressController {

    @Resource
    private OrderAddressService orderAddressService;

    @ApiOperation(value = "收货地址列表",response = Response.class)
    @PostMapping("/list")
    public Response list(@RequestBody @Valid OrderAddressReq  orderAddressReqReq) throws IllegalAccessException {
        return Response.rspData(orderAddressService.orderAddressPageList(orderAddressReqReq));
    }


    @ApiOperation(value = "新增收货地址",response = Response.class)
    @PostMapping("/save")
    public Response save(@RequestBody OrderAddressReq orderAddressReqReq) throws IllegalAccessException {
        return Response.rspData(orderAddressService.saveOrderAddress(orderAddressReqReq));
    }

    @ApiOperation(value = "修改收货地址",response = Response.class)
    @PostMapping("/update")
    public Response update(@RequestBody OrderAddressReq orderAddressReqReq) throws IllegalAccessException {
        return Response.rspData(orderAddressService.updateOrderAddress(orderAddressReqReq));
    }


    @ApiOperation(value = "删除收货地址",response = Response.class)
    @GetMapping("/delete")
    public Response delete(@RequestParam(value = "id") Integer id) {
        return Response.rspData(orderAddressService.removeById(id));
    }

}
