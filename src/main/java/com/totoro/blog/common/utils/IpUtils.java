package com.totoro.blog.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author linhnguyen
 * @version 1.0
 * @classname IpUtils
 * @description change desc here!!!
 * @date 28/01/2020
 */
@Slf4j
public class IpUtils {
    public static String getIPAddr(HttpServletRequest request){
        if (request == null){ return "unknown"; }

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
        if ("127.0.0.1".equals(ip)){
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error("Failed to get IP address {}, {}", e.getMessage(), e);
            }
        }

        return ip;
    }
}
