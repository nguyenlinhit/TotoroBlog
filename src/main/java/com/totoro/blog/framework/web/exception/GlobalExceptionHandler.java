package com.totoro.blog.framework.web.exception;

import com.totoro.blog.common.StringUtils;
import com.totoro.blog.common.exception.BaseException;
import com.totoro.blog.common.exception.CustomException;
import com.totoro.blog.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @className: GlobalExceptionHandler
 * @description: Global exception handler
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
@RestController
@Slf4j
public class GlobalExceptionHandler {
    /**
     * Basic exception
     *
     * @param e exception
     * @return operation message
     */
    @ExceptionHandler(BaseException.class)
    public AjaxResult baseException(BaseException e){
        return AjaxResult.error(e.getMessage());
    }

    /**
     * Business exception
     *
     * @param e exception
     * @return operation message
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult businessException(CustomException e){
        if (StringUtils.isNull(e.getCode())){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error(e.getCode(), e.getMessage());
    }

}
