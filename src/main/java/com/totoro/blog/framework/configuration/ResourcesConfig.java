package com.totoro.blog.framework.configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

/**
 * @version 1.0
 * @className: ResourcesConfig
 * @description: Description here!!!
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class ResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String pathUtl = Paths.get("implement here.").normalize().toUri().toASCIIString();
        registry.addResourceHandler("/file/**").addResourceLocations(pathUtl).setCachePeriod(0);

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
