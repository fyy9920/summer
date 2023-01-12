package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Opinion;
import com.summer.entity.WechatUser;
import com.summer.entity.dto.OpinionDto;
import com.summer.entity.request.OpinionPageReq;
import com.summer.entity.request.OpinionReq;
import com.summer.mapper.OpinionMapper;
import com.summer.service.IWechatUserService;
import com.summer.service.OpinionService;
import com.summer.utils.QueryUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author tubo
* @description 针对表【t_opinion】的数据库操作Service实现
* @createDate 2022-12-29 09:51:56
*/
@Service
public class OpinionServiceImpl extends ServiceImpl<OpinionMapper, Opinion>  implements OpinionService{

    @Resource
    private IWechatUserService wechatUserService;

    @Override
    public boolean saveOpinion(OpinionReq opinionReq) {
        Integer createBy = 1;
        Opinion opinion = new Opinion();
        opinion.setCreateBy(createBy);
        opinion.setContent(opinionReq.getContent());
        return save(opinion);
    }


    @Override
    public IPage<OpinionDto> opinionPageList(OpinionPageReq opinionPageReq) throws IllegalAccessException {
        QueryWrapper<Opinion> queryWrapper = QueryUtils.getQueryWrapper(opinionPageReq);
        Page<Opinion> page = new Page<>(opinionPageReq.getPageNum(),opinionPageReq.getPageSize());
        return this.page(page,queryWrapper).convert(opinion -> {
            OpinionDto opinionDto = new OpinionDto();
            BeanUtils.copyProperties(opinion,opinionDto);
            WechatUser user = wechatUserService.getById(opinion.getCreateBy());
            if(user != null ) {
                opinionDto.setCreateName(user.getNickname());
            }
            return opinionDto;
        });
    }
}




