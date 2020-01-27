package com.totoro.blog.common.exception;

import com.totoro.blog.common.MessageUtils;
import com.totoro.blog.common.StringUtils;

/**
 * @version 1.0
 * @className: BaseException
 * @description: The base of exception
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class BaseException extends RuntimeException{
    /*The module*/
    private String module;
    /*Error code*/
    private String code;
    /*The args*/
    private Object[] args;
    /*Error message*/
    private String defaultMessage;

    public BaseException( String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args){
        this(module,code,args,null);
    }

    public BaseException(String module, String defaultMessage){
        this(module,null,null,defaultMessage);
    }

    public BaseException(String code, Object[] args){
        this(null,code,args,null);
    }

    public BaseException(String defaultMessage){
        this(null, null, null, defaultMessage);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)){
            message = MessageUtils.message(code, args);
        }
        if (message == null){
            message = defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
