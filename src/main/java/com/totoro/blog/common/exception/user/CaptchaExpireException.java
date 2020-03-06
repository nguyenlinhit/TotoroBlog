package com.totoro.blog.common.exception.user;

/**
 * @version 1.0
 * @className: CaptchaExpireException
 * @description: If captcha is expired, will throw this exception.
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class CaptchaExpireException extends UserException {
    public CaptchaExpireException(String code, Object[] args) {
        super("user.captcha.expire", null);
    }
}
