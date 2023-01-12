package com.summer.api;

import com.summer.common.PageResult;
import com.summer.common.Response;
import com.summer.entity.dto.ActivityDto;
import com.summer.entity.request.ActivityJoinReq;
import com.summer.entity.request.ActivityPageReq;
import com.summer.entity.request.ActivityReq;
import com.summer.service.IActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tubo
 * @date 2022/12/15
 */
@Api(value = "活动", tags = "活动")
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private IActivityService activityService;

    @ApiOperation(value = "活动列表",response = Response.class)
    @PostMapping("/list")
    public Response list(@RequestBody @Valid ActivityPageReq activityReq) throws IllegalAccessException {
        return Response.rspData(PageResult.success(activityService.activityPageList(activityReq)));
    }

    @ApiOperation(value = "参加活动--支付")
    @PostMapping("/join/activity")
    public Response joinActivity(@RequestBody @Valid ActivityJoinReq activityJoinReq){
        return Response.rspData(activityService.joinActivity(activityJoinReq));
    }

    @ApiOperation(value = "通过id查询活动详情",response = Response.class)
    @GetMapping("/getById")
    public Response getById(@RequestParam(value = "id") Integer id){
        return Response.rspData(activityService.getActivityById(id));
    }

    @ApiOperation(value = "新增活动")
    @GetMapping("/save")
    public Response saveActivity(@RequestBody @Valid ActivityReq activityReq){
        return Response.rspData(activityService.saveActivity(activityReq));
    }

}
