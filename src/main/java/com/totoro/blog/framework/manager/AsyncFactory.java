package com.totoro.blog.framework.manager;

import com.totoro.blog.common.utils.IpUtils;
import com.totoro.blog.common.utils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.TimerTask;

/**
 * @version 1.0
 * @className: AsyncFactory
 * @description: Async Factory
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class AsyncFactory {
    public AsyncFactory() {
    }

    public static TimerTask recordLoginLog(final String username, final Boolean status, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIPAddr(ServletUtils.getRequest());

        return new TimerTask() {
            @Override
            public void run() {
                String address = ip + "| please implement getRealAddressByIp";
                String os = userAgent.getOperatingSystem().getName();
                String browser = userAgent.getBrowser().getName();
                // TODO: please implement record login in here!!!
            }
        };
    }
}
