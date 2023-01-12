package com.summer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Home;
import com.summer.entity.request.HomeReq;
import com.summer.exception.ServiceException;
import com.summer.mapper.HomeMapper;
import com.summer.service.IHomeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author tubo
 */
@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home> implements IHomeService {

    @Override
    public List<Home> listByCondition() {
        List<Home> list = list();
        return list;
    }


    @Override
    public boolean updateHomeById(HomeReq homeReq) {
        Home home = getById(homeReq.getId());
        Optional.ofNullable(home).orElseThrow(() -> new ServiceException("首页数据不存在"));
        home.setBannerUrl(homeReq.getBannerUrl());
        home.setDetailUrl(homeReq.getDetailUrl());
        home.setTitle(homeReq.getTitle());
        return updateById(home);
    }
}




