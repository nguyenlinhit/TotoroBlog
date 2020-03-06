package com.totoro.blog.framework.security.service;

import com.totoro.blog.common.utils.IdUtils;
import com.totoro.blog.common.utils.IpUtils;
import com.totoro.blog.common.utils.ServletUtils;
import com.totoro.blog.common.utils.StringUtils;
import com.totoro.blog.common.contant.Constants;
import com.totoro.blog.framework.redis.RedisCacheService;
import com.totoro.blog.framework.security.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    private final RedisCacheService redisCacheService;

    public TokenService(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    /**
     * Get user identify information
     *
     * @param request HttpServletRequest
     * @return User info
     */
    public LoginUser getLoginUser(HttpServletRequest request){
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)){
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String userKey = getTokenKey(uuid);
            return redisCacheService.getCacheObject(userKey);
        }
        return null;
    }

    /**
     * Delete user identify information.
     *
     * @param token Token
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCacheService.deleteObject(userKey);
        }
    }

    /**
     * Create Token.
     *
     * @param loginUser Login User
     * @return String
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * Refresh Token.
     *
     * @param loginUser Login User
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);

        String userKey = getTokenKey(loginUser.getToken());
        redisCacheService.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * Set user agent information.
     *
     * @param loginUser Login User
     */
    private void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIPAddr(ServletUtils.getRequest());
        loginUser.setIP(ip);
        //loginUser.setLocation();
    }

    /**
     * Get data claim from token
     *
     * @param token Token
     * @return Data statement
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generate token from data declaration
     *
     * @param claims Data statement
     * @return Token
     */
    public String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Validity period of verification token, less than 20 minutes,
     * automatically refresh the cache.
     *
     * @param loginUser Login User
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();

        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            String token = loginUser.getToken();
            loginUser.setToken(token);
            refreshToken(loginUser);
        }
    }

    /**
     * Get request token
     *
     * @param request http request
     * @return Token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * Get token key
     *
     * @param uuid Token key
     * @return Token
     */
    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * Get Username from Token.
     *
     * @param token Token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
}
