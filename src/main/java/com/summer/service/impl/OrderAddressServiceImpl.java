package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.OrderAddress;
import com.summer.entity.WechatUser;
import com.summer.entity.dto.OrderAddressDto;
import com.summer.entity.request.OrderAddressReq;
import com.summer.exception.ServiceException;
import com.summer.mapper.OrderAddressMapper;
import com.summer.service.IWechatUserService;
import com.summer.service.OrderAddressService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author tubo
* @description 针对表【t_order_address(收货地址表)】的数据库操作Service实现
* @createDate 2023-01-11 09:45:50
*/
@Service
public class OrderAddressServiceImpl extends ServiceImpl<OrderAddressMapper, OrderAddress> implements OrderAddressService{

    @Resource
    private IWechatUserService wechatUserService;

    @Override
    public List<OrderAddressDto> orderAddressPageList(OrderAddressReq orderAddressReq) {

        List<OrderAddress> orderAddressList= list(new LambdaQueryWrapper<OrderAddress>().eq(OrderAddress::getUserId, orderAddressReq.getUserId()));
        List<OrderAddressDto> list = new ArrayList<>();
        orderAddressList.forEach(orderAddress -> {
            OrderAddressDto orderAddressDto = new OrderAddressDto();
            BeanUtils.copyProperties(orderAddress,orderAddressDto);
            WechatUser user = wechatUserService.getById(orderAddress.getUserId());
            if(user != null ) {
                orderAddressDto.setNickName(user.getNickname());
                orderAddressDto.setMobile(user.getMobile());
            }
            list.add(orderAddressDto);
        });
        return list;
    }


    @Override
    public boolean saveOrderAddress(OrderAddressReq orderAddressReqReq) {
        if(StringUtils.isBlank(orderAddressReqReq.getAddress())){
            throw new ServiceException("收货地址不能为空!");
        }
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(orderAddressReqReq,orderAddress);
        orderAddress.setCreateBy(1);
        orderAddress.setUserId(1);
        return save(orderAddress);
    }


    @Override
    public boolean updateOrderAddress(OrderAddressReq orderAddressReqReq) {
        Optional.ofNullable(orderAddressReqReq.getId()).orElseThrow(() -> new ServiceException("id不能为空!"));
        Optional.ofNullable(getById(orderAddressReqReq.getId())).orElseThrow(() -> new ServiceException("修改的收货地址不存在!"));
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(orderAddressReqReq,orderAddress);
        orderAddress.setUpdateBy(1);
        return updateById(orderAddress);
    }
}




