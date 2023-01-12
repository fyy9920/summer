package com.summer.service;

import com.summer.entity.Camper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.request.CamperListReq;
import com.summer.entity.request.CamperReq;

import java.util.List;

/**
* @author tubo
* @description 针对表【t_camper】的数据库操作Service
* @createDate 2023-01-03 14:49:18
*/
public interface CamperService extends IService<Camper> {
    /**
     * 添加营员
     * @param camperReq
     * @return
     */
    boolean saveCamper(CamperReq camperReq);

    /**
     *
     * @param camperListReq
     * @return
     */
    List<Camper> listByCondition(CamperListReq camperListReq);
}
