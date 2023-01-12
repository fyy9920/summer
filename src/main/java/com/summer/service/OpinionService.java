package com.summer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.Opinion;
import com.summer.entity.dto.OpinionDto;
import com.summer.entity.request.OpinionPageReq;
import com.summer.entity.request.OpinionReq;

/**
* @author tubo
* @description 针对表【t_opinion】的数据库操作Service
* @createDate 2022-12-29 09:51:56
*/
public interface OpinionService extends IService<Opinion> {

    boolean saveOpinion(OpinionReq opinionReq);

    IPage<OpinionDto> opinionPageList(OpinionPageReq opinionPageReq) throws IllegalAccessException;
}
