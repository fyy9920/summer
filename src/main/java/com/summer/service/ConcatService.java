package com.summer.service;

import com.summer.entity.Concat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.request.ConcatReq;

/**
* @author tubo
* @description 针对表【t_concat(合作联系)】的数据库操作Service
* @createDate 2022-12-29 10:24:39
*/
public interface ConcatService extends IService<Concat> {

    boolean updateContact(ConcatReq concatReq);
}
