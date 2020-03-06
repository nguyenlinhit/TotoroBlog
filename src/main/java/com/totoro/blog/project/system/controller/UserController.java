package com.totoro.blog.project.system.controller;

import com.totoro.blog.common.contant.Constants;
import com.totoro.blog.common.contant.UserConstants;
import com.totoro.blog.common.enums.UserStatus;
import com.totoro.blog.framework.security.service.SysLoginService;
import com.totoro.blog.framework.security.service.TokenService;
import com.totoro.blog.framework.web.controller.BaseController;
import com.totoro.blog.framework.web.domain.AjaxResult;
import com.totoro.blog.framework.web.page.TableDataInfo;
import com.totoro.blog.project.system.domain.User;
import com.totoro.blog.project.system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @className: UserController
 * @description: User information
 * @author: Linh.Nguyen
 * @date: 26/01/2020
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private final UserService userService;
    private final TokenService tokenService;
    private final SysLoginService loginService;

    public UserController(UserService userService, TokenService tokenService, SysLoginService loginService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.loginService = loginService;
    }

    /**
     * Get user list
     *
     * @param user User
     * @return User list
     */
    @GetMapping("/list")
    public TableDataInfo list(User user) {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * Add new user.
     *
     * @param user User
     */
    @PostMapping
    public AjaxResult add(@RequestBody User user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getName()))) {
            return AjaxResult.error("Add new user " + user.getName() + " fail, login account already exists.");
        }
        return toAjax(userService.insert(user));
    }
}
