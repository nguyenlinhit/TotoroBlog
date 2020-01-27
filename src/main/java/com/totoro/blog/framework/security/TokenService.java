package com.totoro.blog.framework.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @className: TokenService
 * @description: Token verification processing
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 */
@Component
public class TokenService {
    @Value("$token.header")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = MILLIS_SECOND*60;
    private static final Long MILLIS_MINUTE_TEN = 20*60*1000L;
    
}
