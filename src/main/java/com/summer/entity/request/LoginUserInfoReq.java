package com.summer.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author tubo
 * @date 2023/1/10
 */
@Data
public class LoginUserInfoReq {

    @NotBlank(message = "加密数据不能为空")
    private String encryptedData;

    @NotBlank(message = "不能为空")
    private String iv;

    @NotBlank(message = "不能为空")
    private String sessionKey;

    @NotBlank(message = "不能为空")
    private String openId;

    //@NotBlank(message = "rowData不能为空")
    private String rowData;

}
