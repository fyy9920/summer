package com.summer.common;

import com.summer.enums.ResponseEnum;
import lombok.Data;

/**
 * @author tubo
 * 自定义响应类
 * @date 2022/12/15
 */
@Data
public class Response<T> {

    private String code;

    private String msg;

    private T data;

    /**
     * @return
     * @title 成功消息
     */
    public static <T> Response<T> success() {
        return rspMsg(ResponseEnum.SUCCESS);
    }

    /**
     * @return
     * @title 失败消息
     */
    public static <T> Response<T> fail() {
        return rspMsg(ResponseEnum.SERVER_INNER_ERR);
    }

    /**
     * @param responseEnum
     * @return
     * @title 自定义消息
     */
    public static <T> Response<T> rspMsg(ResponseEnum responseEnum) {
        Response<T> message = new Response<>();
        message.setCode(responseEnum.getCode());
        message.setMsg(responseEnum.getMsg());
        return message;
    }

    /**
     * @param code
     * @param msg
     * @return
     * @title 自定义消息
     */
    public static <T> Response<T> rspMsg(String code, String msg) {
        Response<T> message = new Response<>();
        message.setCode(code);
        message.setMsg(msg);
        return message;
    }

    /**
     * @param data
     * @return
     * @title 返回数据
     */
    public static <T> Response<T> rspData(T data) {
        Response<T> responseData = new Response<>();
        responseData.setCode(ResponseEnum.SUCCESS.getCode());
        responseData.setData(data);
        return responseData;
    }

    /**
     * @param data
     * @return
     * @title 返回数据-自定义code
     */
    public static <T> Response<T> rspData(String code, T data) {
        Response<T> responseData = new Response<>();
        responseData.setCode(code);
        responseData.setData(data);
        return responseData;
    }
}
