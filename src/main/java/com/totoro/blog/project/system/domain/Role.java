package com.totoro.blog.project.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.totoro.blog.framework.web.domain.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @className: Role
 * @description: Role table
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseEntity implements Serializable {
    private Long id;

    /**
    * Role Name
    */
    private String name;

    /**
     *
    * Role Permission String
    */
    private String key;

    /**
    * Display Order
    */
    private Integer sort;

    /**
    * Role Status (0: disable, 1: enable)
    */
    private Byte status;

    private boolean flag = false;

    private static final long serialVersionUID = 1L;

    private static boolean isAdmin(Long roleID){
        return roleID != null && 1L == roleID;
    }

    public boolean isAdmin(){
        return isAdmin(this.id);
    }
}