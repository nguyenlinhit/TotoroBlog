package com.totoro.blog.project.system.service.Impl;

import org.springframework.stereotype.Service;
import com.totoro.blog.project.system.mapper.UserMapper;
import com.totoro.blog.project.system.domain.User;
import com.totoro.blog.project.system.service.UserService;

import java.util.List;

/**
 * @className: UserServiceImpl
 * @description: description here!!!
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

}
