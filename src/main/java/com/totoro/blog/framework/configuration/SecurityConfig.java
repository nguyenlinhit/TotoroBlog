package com.totoro.blog.framework.configuration;

import com.totoro.blog.framework.security.filter.JwtAuthenticationTokenFilter;
import com.totoro.blog.framework.security.handle.AuthenticationEntryPointImpl;
import com.totoro.blog.framework.security.handle.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPointImpl unauthorizedHandler;
    private final LogoutSuccessHandlerImpl logoutSuccessHandler;
    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, AuthenticationEntryPointImpl unauthorizedHandler, LogoutSuccessHandlerImpl logoutSuccessHandler, JwtAuthenticationTokenFilter authenticationTokenFilter) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * anyRequest          |   Match all request paths
     * access              |   Accessible when SpringEl expression result is true
     * anonymous           |   Accessible anonymously
     * denyAll             |   User cannot access
     * fullyAuthenticated  |   The user is fully authenticated and can be accessed (automatic login under non-remember-me)
     * hasAnyAuthority     |   If there are parameters, the parameters indicate permissions, then any of them can be accessed
     * hasAnyRole          |   If there are parameters, the parameters represent roles, then any one of the roles can access
     * hasAuthority        |   If there are parameters, the parameters indicate permissions, then their permissions can be accessed
     * hasIpAddress        |   If there is a parameter, the parameter indicates the IP address, if the user IP and the parameter match, you can access
     * hasRole             |   If there is a parameter, the parameter represents the role, then its role can access
     * permitAll           |   Users can access any
     * rememberMe          |   Allow access for users logged in with remember-me
     * authenticated       |   User can access after login
     *
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login", "/captchaImg", "/websocket/**").anonymous()
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/file/**").anonymous()
                .antMatchers("swagger-ui.html").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
//                .antMatchers("/system/user", "/system/user/list").anonymous()
                .antMatchers("/t/**").permitAll()
                .anyRequest().authenticated().and()
                .headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
