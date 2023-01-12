package com.summer.service;

import com.alibaba.fastjson.JSONObject;
import com.summer.entity.WechatUser;
import com.summer.entity.request.LoginReq;
import com.summer.entity.request.LoginUserInfoReq;

/**
 * @author tubo
 * @date 2022/12/15
 */
public interface ILoginService {
    /**
     * 微信小程序登录
     * @param loginReq
     * @return
     */
    JSONObject wxLogin(LoginReq loginReq);

    /**
     * 获取微信用户信息
     * @return
     */
    WechatUser getWxLoginPhone(LoginUserInfoReq loginUserInfoReq);

    /**
     * 解密手机号
     * @param code
     * @return
     */
    JSONObject decodePhone(String code);
}
