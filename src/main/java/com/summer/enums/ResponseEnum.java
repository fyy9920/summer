package com.summer.enums;

/**
 * @author tubo
 * @date 2022/12/15
 */
public enum ResponseEnum {
    // 可以根据自己的实际需要增加状态码
    SUCCESS("200", "ok"),
    SERVER_INNER_ERR("500","系统繁忙"),
    PARAM_LACK("100" , "非法参数"),
    OPERATION_FAILED("101" ,"操作失败"),
    FILED_FAILED("102" ,"文件上传失败"),
    VALID_FAILED("103" ,"签名校检失败");

    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
