package com.totoro.blog.common;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @version 1.0
 * @className: MessageUtils
 * @description: Get i18n resource file
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class MessageUtils {
    public MessageUtils() {
    }

    /**
     * Get message according to message key
     * and parameter delegate to spring messageSource
     *
     * @param code Message key
     * @param args Parameter
     * @return Get internationalized translation values
     */
    public static String message(String code, Object... args){
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
