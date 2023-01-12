package com.summer.service;

import com.summer.entity.Home;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.request.HomeReq;

import java.util.List;

/**
 * @author tubo
 */
public interface IHomeService extends IService<Home> {

    /**
     * 根据条件查询首页数据
     */
    List<Home> listByCondition();

    boolean updateHomeById(HomeReq homeReq);
}
