package com.totoro.blog.common.exception.user;

/**
 * @version 1.0
 * @className: CaptchaException
 * @description: If captcha error will throw this exception.
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class CaptchaException extends UserException {
    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
