package com.totoro.blog.project.system.controller;

import com.totoro.blog.common.contant.Constants;
import com.totoro.blog.framework.security.service.SysLoginService;
import com.totoro.blog.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @className: LoginController
 * @description: Login Controller.
 * @author: LinhNguyen
 * @date: 3/1/2020
 */
@RestController
public class LoginController {

    private final SysLoginService loginService;

    public LoginController(SysLoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Login Method
     *
     * @param username User Name
     * @param password Password
     * @param code     Code
     * @param uuid     UUID
     * @return AjaxResult
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid) {
        AjaxResult ajax = AjaxResult.success();
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
