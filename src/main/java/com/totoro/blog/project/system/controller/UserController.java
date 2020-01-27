package com.totoro.blog.project.system.controller;

import com.totoro.blog.framework.web.controller.BaseController;
import com.totoro.blog.framework.web.page.TableDataInfo;
import com.totoro.blog.project.system.domain.User;
import com.totoro.blog.project.system.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user list
     *
     * @param user User
     * @return User list
     */
    @GetMapping("/list")
    public TableDataInfo list(User user){
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }
}
