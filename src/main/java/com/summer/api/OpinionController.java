package com.summer.api;

import com.summer.common.PageResult;
import com.summer.common.Response;
import com.summer.entity.request.OpinionPageReq;
import com.summer.entity.request.OpinionReq;
import com.summer.service.OpinionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tubo
 * @date 2022/12/15
 */
@Api(value = "意见反馈", tags = "意见反馈")
@RestController
@RequestMapping("/opinion")
public class OpinionController {
    @Resource
    private OpinionService opinionService;

    @ApiOperation(value = "意见反馈列表",response = Response.class)
    @PostMapping("/list")
    public Response list(@RequestBody OpinionPageReq opinionPageReq) throws IllegalAccessException {
        return Response.rspData(PageResult.success(opinionService.opinionPageList(opinionPageReq)));
    }

    @ApiOperation(value = "新增意见")
    @PostMapping("/save/opinion")
    public Response saveOpinion(@RequestBody @Valid OpinionReq opinionReq){
        return Response.rspData(opinionService.saveOpinion(opinionReq));
    }

    @ApiOperation(value = "通过id删除意见")
    @GetMapping("/removeById")
    public Response removeById(@RequestParam(value = "id") Integer id){
        return Response.rspData(opinionService.removeById(id));
    }

}
