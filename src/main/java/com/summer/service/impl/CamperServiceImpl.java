package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Camper;
import com.summer.entity.request.CamperListReq;
import com.summer.entity.request.CamperReq;
import com.summer.exception.ServiceException;
import com.summer.service.CamperService;
import com.summer.mapper.CamperMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author tubo
* @description 针对表【t_camper】的数据库操作Service实现
* @createDate 2023-01-03 14:49:18
*/
@Service
public class CamperServiceImpl extends ServiceImpl<CamperMapper, Camper> implements CamperService{

    @Override
    public boolean saveCamper(CamperReq camperReq) {
        //判断身份证或名称是否重复
        List<Camper> camperList = list(new LambdaQueryWrapper<Camper>().eq(Camper::getName, camperReq.getName()).or().eq(Camper::getIdCard, camperReq.getIdCard()));

        if(CollectionUtils.isNotEmpty(camperList)){
            throw new ServiceException("名字或身份证号已存在，请重新输入！");
        }
        Camper camper = new Camper();
        BeanUtils.copyProperties(camperReq,camper);
        camper.setCreateBy(1);
        camper.setUserId(1);
        return save(camper);
    }

    @Override
    public List<Camper> listByCondition(CamperListReq camperListReq) {
        List<Camper> list = list(new LambdaQueryWrapper<Camper>().eq(Camper::getUserId, camperListReq.getUserId()));
        return list;
    }
}




