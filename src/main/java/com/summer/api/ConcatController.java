package com.summer.api;

import com.summer.common.Response;
import com.summer.entity.request.ConcatReq;
import com.summer.service.ConcatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tubo
 * @date 2022/12/15
 */
@Api(value = "合作联系", tags = "合作联系")
@RestController
@RequestMapping("/contact")
public class ConcatController {
    @Resource
    private ConcatService concatService;

    @ApiOperation(value = "合作联系",response = Response.class)
    @PostMapping("/list")
    public Response list() throws IllegalAccessException {
        return Response.rspData(concatService.list());
    }

    @ApiOperation(value = "通过id修改合作联系方式")
    @PostMapping("/update")
    public Response updateContact(@RequestBody ConcatReq concatReq){
        return Response.rspData(concatService.updateContact(concatReq));
    }

}
