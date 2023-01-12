package com.summer.entity.request;

import lombok.Data;

/**
 * @author tubo
 * @date 2023/1/10
 */

@Data
public class LoginReq {


    private String code;
    private String rawData;
    private String signature;

}
