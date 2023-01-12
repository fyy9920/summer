package com.summer.api;

import com.alibaba.fastjson.JSONObject;
import com.summer.common.Response;
import com.summer.entity.WechatUser;
import com.summer.entity.request.LoginReq;
import com.summer.entity.request.LoginUserInfoReq;
import com.summer.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tubo
 * @date 2022/12/15
 */
@RestController
@RequestMapping("/wx")
@Api(value = "登录", tags = "登录")
public class LoginController {
    @Resource
    private ILoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "微信登录")
    public Response wxLogin(@RequestBody @Valid LoginReq loginReq) {

        return Response.rspData(loginService.wxLogin(loginReq));
    }

    @PostMapping("/getWxLoginPhone")
    @ApiOperation(value = "微信登录获取用户信息")
    public Response getWxLoginPhone(@RequestBody @Valid LoginUserInfoReq loginUserInfoReq) {
        WechatUser wxInfo = loginService.getWxLoginPhone(loginUserInfoReq);
        return Response.rspData(wxInfo);

    }

    @GetMapping("/decodePhone")
    @ApiOperation(value = "微信登录获取手机号")
    public Response decodePhone(String code) {
        JSONObject jsonObject = loginService.decodePhone(code);
        return Response.rspData(jsonObject);

    }

}
