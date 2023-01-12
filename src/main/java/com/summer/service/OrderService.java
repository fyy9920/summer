package com.summer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.summer.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.dto.OrderDto;
import com.summer.entity.request.OrderPageReq;

import java.util.List;

/**
* @author tubo
* @description 针对表【t_order(订单表)】的数据库操作Service
* @createDate 2023-01-05 16:24:17
*/
public interface OrderService extends IService<Order> {

    IPage<OrderDto> orderPageList(OrderPageReq orderPageReq) throws IllegalAccessException;
}
