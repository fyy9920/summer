package com.summer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.summer.constant.WXConstant;
import com.summer.entity.WechatUser;
import com.summer.entity.request.LoginReq;
import com.summer.entity.request.LoginUserInfoReq;
import com.summer.exception.ServiceException;
import com.summer.service.ILoginService;
import com.summer.service.IWechatUserService;
import com.summer.utils.HttpClientUtil;
import com.summer.utils.JsonUtil;
import com.summer.utils.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * @author tubo
 */
@Service
@Slf4j
public class LoginServiceImpl  implements ILoginService {

    @Resource
    private IWechatUserService userService;

    @Override
    public JSONObject wxLogin(LoginReq loginReq) {
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(loginReq.getCode());
        // 3.接收微信接口服务 获取返回的参数
        String openId = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        LambdaQueryWrapper<WechatUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(WechatUser::getOpenId, openId);
        WechatUser user = userService.getOne(lqw);
        // 用户非敏感信息：rawData
        // 签名：signature
        WechatUser wechatUser = JSON.parseObject(String.valueOf(SessionKeyOpenId),WechatUser.class);
        wechatUser.setOpenId(openId);
        if (user == null) {
            // 用户信息入库
            userService.save(wechatUser);
        }else{
            // 已存在，更新用户的信息
            UpdateWrapper<WechatUser> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("open_id",openId)
                    .set("open_id",openId);
                    //.set("avatar_url",wechatUser.getAvatarUrl())
                    //.set("gender",wechatUser.getGender())
                    //.set("country",wechatUser.getCountry())
                    //.set("province",wechatUser.getProvince())
                    //.set("city",wechatUser.getCity())
                    //.set("mobile",wechatUser.getMobile());
            userService.update(updateWrapper);
        }
        //生成token
        String token = "";
        return SessionKeyOpenId;
    }


    @Override
    public WechatUser getWxLoginPhone(LoginUserInfoReq loginUserInfoReq) {

        System.out.println(loginUserInfoReq.getRowData());

        //数字签名验证成功,解密
        String infos = this.decrypt(loginUserInfoReq.getSessionKey(), loginUserInfoReq.getIv(), loginUserInfoReq.getEncryptedData());
        //反序列化 JSON 数据
        WechatUser wechatUser = JSONObject.parseObject(infos, WechatUser.class);
        wechatUser.setOpenId(loginUserInfoReq.getOpenId());

        LambdaQueryWrapper<WechatUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(WechatUser::getOpenId, loginUserInfoReq.getOpenId());
        WechatUser user = userService.getOne(queryWrapper);
        if(user != null ) {
            wechatUser.setId(user.getId());
        }
        //更新数据库
        this.userService.updateById(wechatUser);
        return wechatUser;
    }


    @Override
    public JSONObject decodePhone(String code) {
        JSONObject phone;
        // 获取token
        String token_url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", WXConstant.APPID, WXConstant.SECRET);
        try {
            JSONObject token = JSON.parseObject(HttpClientUtil.doGet(token_url));
            if (token == null) {
                log.info("获取token失败");
                return null;
            }
            String accessToken = token.getString("access_token");
            if (StringUtils.isEmpty(accessToken)) {
                log.info("获取token失败");
                return null;
            }
            log.info("token : {}", accessToken);
            //获取phone
            String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber"
                    + "?access_token=" + accessToken;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", code);
            String reqJsonStr = JsonUtil.objToString(jsonObject);
            phone = JSON.parseObject(HttpClientUtil.doPostJson(url, reqJsonStr));

            if (phone == null) {
                log.info("获取手机号失败");
                return null;
            }
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String decrypt(String session_key, String iv, String encryptData) {

        String decryptString = "";
        //解码经过 base64 编码的字符串
        byte[] sessionKeyByte = Base64.getDecoder().decode(session_key);
        byte[] ivByte = Base64.getDecoder().decode(iv);
        byte[] encryptDataByte = Base64.getDecoder().decode(encryptData);

        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            //得到密钥
            Key key = new SecretKeySpec(sessionKeyByte, "AES");
            //AES 加密算法
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
            algorithmParameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
            byte[] bytes = cipher.doFinal(encryptDataByte);
            decryptString = new String(bytes);
        } catch (Exception e) {
            throw new ServiceException("解密失败，请重新调用");
        }
        return decryptString;
    }
}




