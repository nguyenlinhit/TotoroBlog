package com.totoro.blog.common.enums;

/**
 * @version 1.0
 * @className: UserStatus
 * @description: User status
 * @author: LinhNguyen
 * @date: 3/1/2020
 */
public enum UserStatus {
    OK("0", "Active"), DISABLE("1", "Inactive");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
