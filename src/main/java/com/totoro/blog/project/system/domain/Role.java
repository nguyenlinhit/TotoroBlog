package com.totoro.blog.project.system.domain;

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
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Role extends BaseEntity implements Serializable {
    private Long id;

    /**
    * Role Name
    */
    private String name;

    /**
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

    private static final long serialVersionUID = 1L;
}