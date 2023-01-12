package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.*;
import com.summer.entity.dto.OrderDto;
import com.summer.entity.request.OrderPageReq;
import com.summer.enums.OdrderDeliveryStatus;
import com.summer.enums.OdrderPayStatus;
import com.summer.mapper.OrderMapper;
import com.summer.service.*;
import com.summer.utils.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author tubo
* @description 针对表【t_order(订单表)】的数据库操作Service实现
* @createDate 2023-01-05 16:24:17
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

    @Resource
    private IWechatUserService wechatUserService;

    @Resource
    private CamperService camperService;

    @Resource
    private IActivityService activityService;

    @Resource
    private OrderAddressService orderAddressService;

    @Override
    public IPage<OrderDto> orderPageList(OrderPageReq orderPageReq) throws IllegalAccessException {
        QueryWrapper<Order> queryWrapper = QueryUtils.getQueryWrapper(orderPageReq);
        Page<Order> page = new Page<>(orderPageReq.getPageNum(),orderPageReq.getPageSize());
        return this.page(page,queryWrapper).convert(order -> {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order,orderDto);
            //用户名字
            if(order.getUserId() != null ) {
                WechatUser user = wechatUserService.getById(order.getUserId());
                if(user != null ) {
                    orderDto.setUserName(user.getNickname());
                }
            }
            //营员名字
            if(StringUtils.isNotBlank(order.getCamperId()) ) {
                String[] split = order.getCamperId().split(StringPool.COMMA);
                List<String> list = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    Camper camper = camperService.getById(split[i]);
                    list.add(camper.getName());
                }
                orderDto.setCamperName(list);
            }
            //活动标题
            if(order.getActivityId() != null ) {
                Activity activity = activityService.getById(order.getActivityId());
                if(activity != null ) {
                    orderDto.setActivityTitle(activity.getTitle());
                    orderDto.setActivityImg(activity.getIndexUrl());
                }
            }
            //收货地址
            if(order.getAddressId() != null ) {
                OrderAddress orderAddress = orderAddressService.getById(order.getAddressId());
                if(orderAddress != null ) {
                    orderDto.setAddress(orderAddress.getAddress());
                    orderDto.setProvince(orderAddress.getProvince());
                    orderDto.setCity(orderAddress.getCity());
                    orderDto.setDistrict(orderAddress.getDistrict());
                }
            }
            //订单状态和支付状态
            orderDto.setDeliveryStatusName((String) OdrderDeliveryStatus.getEnumValue(OdrderDeliveryStatus.class,order.getDeliveryStatus()));
            orderDto.setPayStatusName((String) OdrderPayStatus.getEnumValue(OdrderPayStatus.class,order.getStatus()));
            return orderDto;
        });
    }
}




