package com.totoro.blog.project.system.mapper;

import com.totoro.blog.project.system.domain.User;

import java.util.List;

/**
 * @className: UserMapper
 * @description: User table data mapper
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 * @version 1.0
 */
public interface UserMapper {
    int insert(User record);
    List<User> selectUserList(User user);
    User selectUserByUserName(String userName);
}