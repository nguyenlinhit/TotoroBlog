package com.totoro.blog.project.system.service;

import com.totoro.blog.project.system.domain.User;

import java.util.List;

/**
 * @className: UserService
 * @description: description here!!!
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 * @version 1.0
 */
public interface UserService{
    int insert(User record);
    List<User> selectUserList(User user);
}
