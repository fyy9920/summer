package com.summer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.entity.Activity;
import com.summer.entity.Comment;
import com.summer.entity.WechatUser;
import com.summer.entity.dto.ActivityDto;
import com.summer.entity.dto.CommentDto;
import com.summer.entity.request.CommentPageReq;
import com.summer.entity.request.CommentReq;
import com.summer.service.CommentService;
import com.summer.mapper.CommentMapper;
import com.summer.utils.QueryUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
* @author tubo
* @description 针对表【t_comment(评论表)】的数据库操作Service实现
* @createDate 2023-01-04 15:21:23
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Resource
    private WechatUserServiceImpl wechatUserService;
    @Override
    public boolean saveComment(CommentReq commentReq) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentReq,comment);
        comment.setUserId(1);
        comment.setCreateBy(1);
        return save(comment);
    }


    @Override
    public IPage<CommentDto> commentPageList(CommentPageReq commentPageReq) throws IllegalAccessException {
        QueryWrapper<Comment> queryWrapper = QueryUtils.getQueryWrapper(commentPageReq);
        Page<Comment> page = new Page<>(commentPageReq.getPageNum(), commentPageReq.getPageSize());
        return this.page(page, queryWrapper).convert(comment -> {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);

            WechatUser user = wechatUserService.getById(comment.getUserId());
            if(user != null) {
                commentDto.setAvatarUrl(user.getAvatarUrl());
                commentDto.setNickname(user.getNickname());
            }
            return commentDto;
        });
    }
}




