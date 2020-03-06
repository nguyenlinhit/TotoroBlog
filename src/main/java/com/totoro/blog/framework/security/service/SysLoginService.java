package com.totoro.blog.framework.security.service;

import com.totoro.blog.common.contant.Constants;
import com.totoro.blog.common.exception.CustomException;
import com.totoro.blog.common.exception.user.CaptchaException;
import com.totoro.blog.common.exception.user.UserPasswordNotMatchException;
import com.totoro.blog.framework.redis.RedisCacheService;
import com.totoro.blog.framework.security.LoginUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @className: SysLoginService
 * @description: Login verification method.
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
@Component
public class SysLoginService {
    private final TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    private final RedisCacheService redisCacheService;

    public SysLoginService(TokenService tokenService, RedisCacheService redisCacheService) {
        this.tokenService = tokenService;
        this.redisCacheService = redisCacheService;
    }

    /**
     * Login authentication.
     *
     * @param username UserName
     * @param password Password
     * @param code     Code
     * @param uuid     UUID
     * @return String
     */
    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCacheService.getCacheObject(verifyKey);
        if (captcha == null) {
            //TODO: Need implement record login in here!!!
            throw new CaptchaException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            //TODO: Need implement record login in here!!!
            throw new CaptchaException();
        }

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //TODO: Need implement record login in here!!!
                throw new UserPasswordNotMatchException();
            } else {
                //TODO: Need implement record login in here!!!
                throw new CustomException(e.getMessage());
            }
        }

        //TODO: Need implement record login in here!!!
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return tokenService.createToken(loginUser);
    }
}
