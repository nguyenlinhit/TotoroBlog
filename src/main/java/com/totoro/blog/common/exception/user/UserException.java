package com.totoro.blog.common.exception.user;

import com.totoro.blog.common.exception.BaseException;

/**
 * @version 1.0
 * @className: UserException
 * @description: Description here!!!
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
