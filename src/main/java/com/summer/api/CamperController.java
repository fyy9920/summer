package com.summer.api;

import com.summer.common.Response;
import com.summer.entity.request.CamperListReq;
import com.summer.entity.request.CamperReq;
import com.summer.service.CamperService;
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
 * @date 2022/12/30
 */
@Api(value = "营员", tags = "营员")
@RestController
@RequestMapping("/camper")
public class CamperController {

    @Resource
    private CamperService camperService;

    @ApiOperation(value = "营员列表",response = Response.class)
    @PostMapping("/list")
    public Response list(@RequestBody @Valid CamperListReq camperListReq) throws IllegalAccessException {
        return Response.rspData(camperService.listByCondition(camperListReq));
    }

    @ApiOperation(value = "新增营员")
    @PostMapping("/save")
    public Response saveCamper(@RequestBody @Valid CamperReq camperReq){
        return Response.rspData(camperService.saveCamper(camperReq));
    }

}
