package com.totoro.blog.framework.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @className: TotoroBlog
 * @description: Get project setting
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
@Component
@ConfigurationProperties(prefix = "totoro")
public class TotoroBlogConfig {
    private String name;
    private String version;
    private String copyrightYear;
    private static String profile;
    private static boolean addressEnabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getProfile() {
        return profile;
    }

    public static void setProfile(String profile) {
        TotoroBlogConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static void setAddressEnabled(boolean addressEnabled) {
        TotoroBlogConfig.addressEnabled = addressEnabled;
    }
}
