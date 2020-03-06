package com.totoro.blog.project.system.mapper;

import com.totoro.blog.project.system.domain.User;

import java.util.List;

/**
 * @version 1.0
 * @className: UserMapper
 * @description: User table data mapper
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public interface UserMapper {
    /**
     * Add user information.
     *
     * @param user User info
     * @return int
     */
    int insert(User user);

    /**
     * Retrieve user list by conditions
     *
     * @param user User info
     * @return List<User>
     */
    List<User> selectUserList(User user);

    /**
     * Retrieve user by user name
     *
     * @param userName User name
     * @return User
     */
    User selectUserByUserName(String userName);

    /**
     * Check unique user name
     *
     * @param userName User name
     * @return int
     */
    int checkUserNameUnique(String userName);
}