package com.totoro.blog.common.exception.user;

/**
 * @version 1.0
 * @className: UserPasswordNotMatchException
 * @description: User not exist or Password not match
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class UserPasswordNotMatchException extends UserException {
    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
