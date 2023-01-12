package com.summer.api;

import com.summer.common.Response;
import com.summer.entity.request.HomeReq;
import com.summer.service.IHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author tubo
 * 首页
 * @date 2022/12/15
 */
@Api(value = "首页", tags = "首页")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    private IHomeService homeService;

    @ApiOperation(value = "首页列表",response = Response.class)
    @PostMapping("/list")
    public Response list(){
        return Response.rspData(homeService.listByCondition());
    }

    @ApiOperation(value = "根据id查询首页详情")
    @GetMapping("/getHomeById")
    public Response getHomeById(@RequestParam("id") Integer id){
        return Response.rspData(homeService.getById(id));
    }

    @ApiOperation(value = "根据id删除首页详情")
    @GetMapping("/removeHomeById")
    public Response removeHomeById(@RequestParam("id") Integer id){
        return Response.rspData(homeService.removeById(id));
    }

    @ApiOperation(value = "根据id修改首页")
    @PostMapping("/updateHomeById")
    public Response updateHomeById(@RequestBody HomeReq homeReq){
        return Response.rspData(homeService.updateHomeById(homeReq));
    }
}
