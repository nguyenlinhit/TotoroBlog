package com.totoro.blog.project.system.service;

import com.totoro.blog.project.system.domain.User;

import java.util.List;

/**
 * @version 1.0
 * @className: UserService
 * @description: description here!!!
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public interface UserService {
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
     * Retrieve user by user name.
     *
     * @param userName User name
     * @return User
     */
    User selectUserByUserName(String userName);

    /**
     * Check unique user name when add new user.
     *
     * @param userName User name
     * @return String
     */
    String checkUserNameUnique(String userName);

    /**
     * Check unique phone when add new user.
     *
     * @param user User
     * @return String
     */
    String checkPhoneUnique(User user);

    /**
     * Check unique email when add new user.
     *
     * @param user User
     * @return String
     */
    String checkEmailUnique(User user);
}
