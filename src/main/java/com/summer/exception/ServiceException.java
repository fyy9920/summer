package com.summer.exception;

/**
 * @author tubo
 * @date 2022/05/26
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg){
        super(msg);
    }
}
