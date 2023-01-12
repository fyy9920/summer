package com.summer.exception;

import com.summer.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * @author tubo
 * @date 2022/10/20
 */
@Slf4j
@RestControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(value = AccessDeniedException.class)
    public Response accessDeniedException(AccessDeniedException accessDeniedException){
        return Response.rspMsg("500",accessDeniedException.getMessage());
    }

    @ExceptionHandler(value = ServiceException.class)
    public Response handleServiceException(ServiceException e) {
        log.error("系统错误", e.getMessage());
        return Response.rspMsg("500",e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return R
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleBindException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return Response.rspMsg("500",message.toString());
    }
}
