package com.summer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.summer.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.entity.dto.CommentDto;
import com.summer.entity.request.CommentPageReq;
import com.summer.entity.request.CommentReq;

import java.util.List;

/**
* @author tubo
* @description 针对表【t_comment(评论表)】的数据库操作Service
* @createDate 2023-01-04 15:21:23
*/
public interface CommentService extends IService<Comment> {

    /**
     * 保存评论
     * @param commentReq
     * @return
     */
    boolean saveComment(CommentReq commentReq);

    /**
     * 查询评论列表
     * @return
     */
    IPage<CommentDto> commentPageList(CommentPageReq commentPageReq) throws IllegalAccessException;
}
