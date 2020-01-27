package com.totoro.blog.project.system.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.totoro.blog.project.system.domain.Role;
import com.totoro.blog.project.system.mapper.RoleMapper;
import com.totoro.blog.project.system.service.RoleService;

import java.util.List;

/**
 * @className: RoleServiceImpl
 * @description: description here!!!
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 * @version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectRoleList(Role role) {
        return roleMapper.selectRoleList(role);
    }

}
