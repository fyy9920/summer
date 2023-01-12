package com.summer.service;

import com.summer.entity.OrderAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.dto.OrderAddressDto;
import com.summer.entity.request.OrderAddressListReq;
import com.summer.entity.request.OrderAddressReq;

import java.util.List;

/**
* @author tubo
* @description 针对表【t_order_address(收货地址表)】的数据库操作Service
* @createDate 2023-01-11 09:45:50
*/
public interface OrderAddressService extends IService<OrderAddress> {

    /**
     * 获取收货地址列表
     * @param orderAddressListReq
     * @return
     */
    List<OrderAddressDto> orderAddressPageList(OrderAddressListReq orderAddressListReq);

    /**
     * 保存收货地址
     * @param orderAddressReqReq
     * @return
     */
    boolean saveOrderAddress(OrderAddressReq orderAddressReqReq);

    /**
     * 修改收货地址
     * @param orderAddressReqReq
     * @return
     */
    boolean updateOrderAddress(OrderAddressReq orderAddressReqReq);
}
