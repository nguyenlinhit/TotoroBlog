package com.totoro.blog.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    /**
     * Generate BCryptPasswordEncoder password
     *
     * @param password Password Encode
     * @return String: password encode
     */
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * @param rawPassword    password input
     * @param encodePassword Password encode
     * @return boolean: true,false
     */
    public static boolean checkMatchesPassword(String rawPassword, String encodePassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodePassword);
    }
}
