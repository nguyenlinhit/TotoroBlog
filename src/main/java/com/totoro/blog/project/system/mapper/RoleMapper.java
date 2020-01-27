package com.totoro.blog.project.system.mapper;

import com.totoro.blog.project.system.domain.Role;

import java.util.List;

/**
 * @className: RoleMapper
 * @description: description here!!!
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 * @version 1.0
 */
public interface RoleMapper {
    Role selectByPrimaryKey(Long id);
    List<Role> selectRoleList(Role role);
}