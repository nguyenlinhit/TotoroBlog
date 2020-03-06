package com.totoro.blog.framework.security.service;

import com.totoro.blog.common.utils.StringUtils;
import com.totoro.blog.common.enums.UserStatus;
import com.totoro.blog.common.exception.BaseException;
import com.totoro.blog.framework.security.LoginUser;
import com.totoro.blog.project.system.domain.User;
import com.totoro.blog.project.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @className: UserDetailsServiceImpl
 * @description: Description here!!!
 * @author: LinhNguyen
 * @date: 3/1/2020
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("Login user: {} does not exist.", username);
            throw new UsernameNotFoundException("Login user: " + username + "does not exist.");
        } else if (user.getDeleteTime() != null) {
            log.info("Login user: {} has been deleted.", username);
            throw new BaseException("Login user: " + username + " has been deleted.");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("Login user: {} inactive", username);
            throw new BaseException("Sorry, your account: " + username + " inactive");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(User user) {
        //TODO: implement Menu permission in here!!!
        return new LoginUser(null, user);
    }
}
