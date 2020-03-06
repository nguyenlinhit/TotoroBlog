package com.totoro.blog.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.totoro.blog.common.utils.ServletUtils;
import com.totoro.blog.common.utils.StringUtils;
import com.totoro.blog.framework.security.LoginUser;
import com.totoro.blog.framework.security.service.TokenService;
import com.totoro.blog.framework.web.domain.AjaxResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @className: LogoutSuccessHandlerImpl
 * @description: Custom exist processing class return success
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final TokenService tokenService;

    public LogoutSuccessHandlerImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String username = loginUser.getUsername();
            tokenService.delLoginUser(loginUser.getToken());
            //TODO: Need implement record logout in here!!!
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.OK, "Logout Successfully.")));
    }
}
