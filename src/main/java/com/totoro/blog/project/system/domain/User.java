package com.totoro.blog.project.system.domain;

import com.totoro.blog.framework.web.domain.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @className: User
 * @description: User table
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {
    private Long id;

    /**
    * User Account
    */
    private String name;

    /**
    * User's Nickname
    */
    private String nickName;

    /**
    * User Type (00: system users)
    */
    private String type;

    /**
    * User's Email
    */
    private String email;

    /**
    * User's Phone
    */
    private String phone;

    /**
    * User's Gender (0: Male, 1: Female, 2: Unknown)
    */
    private String gender;

    /**
    * User's Avatar Address
    */
    private String avatar;

    /**
    * User's Password Account
    */
    private String password;

    /**
    * User's Status (1: active, 0: inactive)
    */
    private String status;

    /**
    * Last Login IP
    */
    private String loginIp;

    /**
    * Last Login Date
    */
    private Date loginDate;

    private static final long serialVersionUID = 1L;

    public User(Long id) {
        this.id = id;
    }

    private List<Role> roles;

    private Long[] roleIDs;

    public boolean isAdmin(){
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long userId){
        return userId != null && 1L == userId;
    }
}