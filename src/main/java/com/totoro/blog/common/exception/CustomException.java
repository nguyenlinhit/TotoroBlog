package com.totoro.blog.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @version 1.0
 * @className: CustomException
 * @description: Custom Exception
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class CustomException extends RuntimeException{
    private HttpStatus code;
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException( String message, Throwable e) {
        super(message,e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getCode() {
        return code;
    }
}
