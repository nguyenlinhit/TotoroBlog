package com.totoro.blog.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.totoro.blog.common.utils.IpUtils;
import com.totoro.blog.common.utils.ServletUtils;
import com.totoro.blog.common.utils.StringUtils;
import com.totoro.blog.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @version 1.0
 * @className: AuthenticationEntryPointImpl
 * @description: Authentication failure processing class returned unauthorized.
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized access: \ncurrent IP:{}\nrequest URI :{}\n", IpUtils.getIPAddr(request), request.getRequestURI());
        String message = StringUtils.format("Unauthorized access: {}, Authentication failed, unable to access system resources ", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED, message)));
    }
}
