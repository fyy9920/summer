package com.summer.api;

import com.summer.common.PageResult;
import com.summer.common.Response;
import com.summer.entity.request.CommentPageReq;
import com.summer.entity.request.CommentReq;
import com.summer.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tubo
 * @date 2022/1/4
 */
@Api(value = "评论", tags = "评论")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "评论列表",response = Response.class)
    @PostMapping("/list")
    public Response list(@RequestBody @Valid CommentPageReq commentPageReq) throws IllegalAccessException {
        return Response.rspData(PageResult.success(commentService.commentPageList(commentPageReq)));
    }


    @ApiOperation(value = "新增评论",response = Response.class)
    @PostMapping("/save")
    public Response saveComment(@RequestBody @Valid CommentReq commentReq)  {
        return Response.rspData(commentService.saveComment(commentReq));
    }

   /* @ApiOperation(value = "通过id修改合作联系方式")
    @PostMapping("/update")
    public Response updateContact(@RequestBody ConcatReq concatReq){
        return Response.rspData(commentService.updateContact(concatReq));
    }*/

}
