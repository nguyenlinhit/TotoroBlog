package com.totoro.blog.project.system.controller;

import com.totoro.blog.framework.web.controller.BaseController;
import com.totoro.blog.framework.web.page.TableDataInfo;
import com.totoro.blog.project.system.domain.Role;
import com.totoro.blog.project.system.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @className: RoleController
 * @description: Role Information
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping("/list")
    public TableDataInfo list(Role role){
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }
}
