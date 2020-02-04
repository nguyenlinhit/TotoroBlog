package com.totoro.blog.framework.security.service;

import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @className: PermissionService
 * @description: Custom permission implementation
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 */
@Service("permissionService")
public class PermissionService {
    /*ALl permission IDs*/
    private static final String ALL_PERMISSION = "*:*:*";
    /*Administrator role permission ID*/
    private static final String SUPER_USER = "admin";
    private static final String ROLE_DELIMITER = ",";

}
