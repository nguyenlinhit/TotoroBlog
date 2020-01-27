package com.totoro.blog.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @className: ServletUtils
 * @description: Client tools
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
@Slf4j
public class ServletUtils {
    public ServletUtils() {
    }

    /**
     * Get string parameter.
     *
     * @param nm Name
     * @return Parameter
     */
    public static String getParameter(String nm){
        return getRequest().getParameter(nm);
    }

    public static Integer getParameterToInt(String nm){
        return ConvertUtils.toInt(getRequest().getParameter(nm));
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static ServletRequestAttributes getRequestAttributes(){
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
