package com.totoro.blog.common.contant;

/**
 * @author linhnguyen
 * @version 1.0
 * @classname Constants
 * @description Constant class
 * @date 28/01/2020
 */
public class Constants {
    public Constants() {
    }

    public static final String LOGIN_SUCCESS = "Success";
    public static final String LOGOUT = "Logout";
    /**
     * The prefix of token
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * Login user redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_token:";
    /**
     * token
     */
    public static final String TOKEN = "token";
    public static final String LOGIN_USER_KEY = "login_user_key";
    public static final String CAPTCHA_CODE_KEY = "captcha_code:";
    public static final Integer CAPTCHA_EXPIRATION = 10;
}
