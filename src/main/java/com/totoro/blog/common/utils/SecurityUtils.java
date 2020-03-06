package com.totoro.blog.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @version 1.0
 * @className: SecurityUtils
 * @description: Security service tools
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class SecurityUtils {
    /**
     * Get Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
