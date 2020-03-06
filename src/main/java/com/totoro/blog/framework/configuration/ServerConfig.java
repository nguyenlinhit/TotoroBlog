package com.totoro.blog.framework.configuration;

import com.totoro.blog.common.utils.ServletUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @className: ServerConfig
 * @description: Service related configuration
 * @author: LinhNguyen
 * @date: 3/1/2020
 */
@Component
public class ServerConfig {
    /**
     * Get the complete request path,
     * including: domain, name, port, context access path.
     *
     * @return String: Service Address
     */
    public String getUrl() {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    private String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getContextPath();

        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
