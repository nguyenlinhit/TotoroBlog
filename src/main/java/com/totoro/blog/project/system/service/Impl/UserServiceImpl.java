package com.totoro.blog.project.system.service.Impl;

import org.springframework.stereotype.Service;
import com.totoro.blog.project.system.mapper.UserMapper;
import com.totoro.blog.project.system.domain.User;
import com.totoro.blog.project.system.service.UserService;

import java.util.List;

/**
 * @version 1.0
 * @className: UserServiceImpl
 * @description: User service implement
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Add user information.
     *
     * @param user User info
     * @return int
     */
    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    /**
     * Retrieve user list by conditions
     *
     * @param user User info
     * @return List<User>
     */
    @Override
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }

    /**
     * Retrieve user by user name
     *
     * @param userName User name
     * @return User
     */
    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * Check unique user name
     *
     * @param userName User name
     * @return int
     */
    @Override
    public int checkUserNameUnique(String userName) {
        return userMapper.checkUserNameUnique(userName);
    }

}
