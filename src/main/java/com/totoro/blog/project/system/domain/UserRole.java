package com.totoro.blog.project.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author linh.nguyen
 * @version 1.0
 * @classname UserRole
 * @description Description here
 * @date 3/17/2020
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole {
    /*User ID*/
    private Long userId;
    /*Role ID*/
    private Long roleId;
}
