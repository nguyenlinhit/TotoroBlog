package com.totoro.blog.framework.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.totoro.blog.project.system.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author linhnguyen
 * @version 1.0
 * @classname LoginUser
 * @description Login user identify permission
 * @date 28/01/2020
 */
@Data
public class LoginUser implements UserDetails {

    private String token;
    private Long loginTime;
    private Long expireTime;
    private String IP;
    private String location;
    private String browser;
    private String os;
    private Set<String> permissions;
    private User user;

    public LoginUser(Set<String> permissions, User user) {
        this.permissions = permissions;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * Whether the account has not expired, which cannot be verified
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Specifies whether the user is unlocked. Locked users cannot authenticate
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (passwords) have expired, which prevents authentication
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Available, disabled users cannot authenticate
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
