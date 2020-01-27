package com.totoro.blog.framework.web.domain;

import org.springframework.http.HttpStatus;
import java.util.HashMap;

/**
 * @version 1.0
 * @className: AjaxResult
 * @description: Operation message HTTP
 * @author: Linh.Nguyen
 * @date: 22/01/2020
 */
public class AjaxResult extends HashMap<String, Object> {
    /**/
    public static final String CODE_TAG = "code";
    /**/
    public static final String MSG_TAG = "msg";
    /**/
    public static final String DATA_TAG = "data";

    /**
     * Initializes newly created AjaxResult object
     * so that it represents an empty message.
     */
    public AjaxResult(){
    }

    /**
     * Initialize a newly created AjaxResult object.
     *
     * @param code Status code
     * @param msg Message
     */
    public AjaxResult(int code, String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * Initialize a newly created AjaxResult object.
     *
     * @param code Http status code
     * @param msg Message
     * @param data Data object
     */
    public AjaxResult(HttpStatus code, String msg, Object data){
        super.put(CODE_TAG, code.value());
        super.put(MSG_TAG, msg);
        /*TODO isNotNull function must be define in StringUtils.*/
        super.put(DATA_TAG, data);
    }

    /**
     * Return success message.
     *
     * @param msg Message
     * @param data Data object
     * @return Success message
     */
    public static AjaxResult success(String msg, Object data){
        return new AjaxResult(HttpStatus.OK, msg, data);
    }

    /**
     * Return success message.
     *
     * @param msg Message
     * @return Success message
     */
    public static AjaxResult success(String msg){
        return AjaxResult.success(msg, null);
    }

    /**
     * Return success message.
     *
     * @param data Data object
     * @return Success message
     */
    public static AjaxResult success(Object data){
        return AjaxResult.success("Operation successful", data);
    }

    /**
     * Return success message.
     *
     * @return Success message
     */
    public static AjaxResult success(){
        return AjaxResult.success("Successful Operation");
    }

    /**
     * Return error message.
     *
     * @param code Status code
     * @param msg Message
     * @return Error message
     */
    public static AjaxResult error(HttpStatus code, String msg){
        return new AjaxResult(code, msg, null);
    }

    /**
     * Return error message.
     *
     * @param msg Message
     * @param data Data object
     * @return Error message
     */
    public static AjaxResult error(String msg, Object data){
        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR, msg, data);
    }

    /**
     * Return error message.
     *
     * @param msg Message
     * @return Error message
     */
    public static AjaxResult error(String msg){
        return AjaxResult.error(msg, null);
    }

    /**
     * Return error message.
     *
     * @return Error message
     */
    public static AjaxResult error(){
        return AjaxResult.error("Operation failed");
    }
}
